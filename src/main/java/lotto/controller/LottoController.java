package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoIssuer;
import lotto.domain.lotto.LottoNumbersGenerator;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.PurchaseAmount;
import lotto.domain.winning.Bonus;
import lotto.domain.winning.Profit;
import lotto.domain.winning.WinningLotto;
import lotto.domain.winning.WinningResult;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {

    private final Input input;
    private final Output output;
    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoController(Input input, Output output, LottoNumbersGenerator lottoNumbersGenerator) {
        this.input = input;
        this.output = output;
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public void play() {
        PurchaseAmount purchaseAmount = purchase();
        LottoTicket lottoTicket = issue(purchaseAmount);
        output.printIssuedLottos(lottoTicket);

        WinningLotto winningLotto = register();
        WinningResult winningResult = winningLotto.checkWinningResult(lottoTicket);
        output.printWinningStatistics(winningResult);

        Profit profit = new Profit(purchaseAmount, winningResult);
        output.printProfitRate(profit.getProfitRate());
    }

    private PurchaseAmount purchase() {
        return retryUntilValid(() -> {
            String inputPurchaseAmount = input.readPurchaseAmount();
            return new PurchaseAmount(inputPurchaseAmount);
        });
    }

    private LottoTicket issue(PurchaseAmount purchaseAmount) {
        LottoIssuer lottoIssuer = new LottoIssuer(purchaseAmount, lottoNumbersGenerator);
        return lottoIssuer.generateLottoTicket();
    }

    private WinningLotto register() {
        Lotto winningLotto = registerWinningNumbers();
        return retryUntilValid(() -> {
            Bonus bonus = registerBonusNumber();
            return new WinningLotto(winningLotto, bonus);
        });
    }

    private Lotto registerWinningNumbers() {
        return retryUntilValid(() -> {
            List<Integer> winningNumbers = input.readWinningNumbers();
            return new Lotto(winningNumbers);
        });
    }

    private Bonus registerBonusNumber() {
        return retryUntilValid(() -> {
            String number = input.readBonusNumber();
            return new Bonus(number);
        });
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e.getMessage());
            }
        }
    }
}

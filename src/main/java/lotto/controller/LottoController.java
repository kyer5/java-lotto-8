package lotto.controller;

import java.util.function.Supplier;
import lotto.domain.lotto.LottoNumbersGenerator;
import lotto.domain.lotto.PurchaseAmount;
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
    }

    private PurchaseAmount purchase() {
        return retryUntilValid(() -> {
            String inputPurchaseAmount = input.readPurchaseAmount();
            return new PurchaseAmount(inputPurchaseAmount);
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

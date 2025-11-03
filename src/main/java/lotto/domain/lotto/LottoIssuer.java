package lotto.domain.lotto;

import java.util.List;
import java.util.stream.IntStream;

public class LottoIssuer {

    private final LottoNumbersGenerator lottoNumbersGenerator;
    private final int quantity;

    public LottoIssuer(PurchaseAmount purchaseAmount, LottoNumbersGenerator lottoNumbersGenerator) {
        this.quantity = purchaseAmount.calculateLottoTicketQuantity();
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public LottoTicket generateLottoTicket() {
        List<Lotto> lottos = IntStream.range(0, quantity)
                .mapToObj(index -> new Lotto(lottoNumbersGenerator.generate()))
                .toList();
        return new LottoTicket(lottos);
    }
}

package lotto.view;

import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoTicket;

public class Output {

    private static final String PURCHASE_COUNT_FORMAT = "%d개를 구매했습니다.";

    public void printIssuedLottos(LottoTicket lottoTicket) {
        System.out.printf("\n" + PURCHASE_COUNT_FORMAT + "\n", lottoTicket.getCount());

        String lottosOutput = lottoTicket.getLottos().stream()
                .map(this::formatLotto)
                .collect(Collectors.joining("\n"));

        System.out.println(lottosOutput);
    }

    private String formatLotto(Lotto lotto) {
        return lotto.getSortedNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
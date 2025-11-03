package lotto.view;

import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.winning.WinningResult;
import lotto.domain.winning.value.Rank;

public class Output {

    private static final String PURCHASE_COUNT_FORMAT = "%d개를 구매했습니다.";
    private static final String WINNING_STATISTICS_MESSAGE = "당첨 통계";
    private static final String DIVIDER = "---";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";
    private static final String WINNING_STATISTICS_FORMAT = "%d개 일치 (%s원) - %d개";
    private static final String WINNING_STATISTICS_WITH_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치 (%s원) - %d개";

    public void printIssuedLottos(LottoTicket lottoTicket) {
        System.out.printf("\n" + PURCHASE_COUNT_FORMAT + "\n", lottoTicket.getCount());

        String lottosOutput = lottoTicket.getLottos().stream()
                .map(this::formatLotto)
                .collect(Collectors.joining("\n"));

        System.out.println(lottosOutput);
    }

    public void printWinningStatistics(WinningResult winningResult) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVIDER);

        for (Rank rank : winningResult.ranksForStatistics()) {
            int count = winningResult.countOf(rank);
            String formatted = formatWinningStatistics(rank, count);
            System.out.println(formatted);
        }
    }

    public void printProfitRate(double profitRate) {
        System.out.printf(PROFIT_RATE_FORMAT, profitRate);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    private String formatLotto(Lotto lotto) {
        return lotto.getSortedNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    private String formatWinningStatistics(Rank rank, int count) {
        String amount = String.format("%,d", rank.getWinningAmount());
        if (rank == Rank.SECOND) {
            return String.format(WINNING_STATISTICS_WITH_BONUS_FORMAT, rank.getMatchCount(), amount, count);
        }
        return String.format(WINNING_STATISTICS_FORMAT, rank.getMatchCount(), amount, count);
    }

    private String formatMoney(int amount) {
        return String.format("%,d", amount);
    }
}
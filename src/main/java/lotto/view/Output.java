package lotto.view;

import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoTicket;
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

    public void printWinningStatistics(Map<Rank, Integer> result) {
        System.out.println("\n" + WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVIDER);

        Rank.getWinningRanks().forEach(rank ->
                System.out.println(formatWinningStatistics(rank, result.getOrDefault(rank, 0)))
        );
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
        if (rank == Rank.SECOND) {
            return String.format(WINNING_STATISTICS_WITH_BONUS_FORMAT,
                    rank.getMatchCount(), formatMoney(rank.getWinningAmount()), count);
        }
        return String.format(WINNING_STATISTICS_FORMAT,
                rank.getMatchCount(), formatMoney(rank.getWinningAmount()), count);
    }

    private String formatMoney(int amount) {
        return String.format("%,d", amount);
    }
}
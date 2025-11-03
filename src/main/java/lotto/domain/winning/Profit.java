package lotto.domain.winning;

import java.util.Map;
import lotto.domain.lotto.PurchaseAmount;
import lotto.domain.winning.value.Rank;

public class Profit {

    private static final int PERCENTAGE_MULTIPLIER = 100;
    private static final int ROUNDING_SCALE = 10;

    private final long totalWinningAmount;
    private final long purchaseAmount;
    private final double profitRate;

    public Profit(PurchaseAmount purchaseAmount, Map<Rank, Integer> result) {
        this.totalWinningAmount = calculateTotalWinningAmount(result);
        this.purchaseAmount = purchaseAmount.getAmount();
        this.profitRate = calculateProfitRate();
    }

    private long calculateTotalWinningAmount(Map<Rank, Integer> result) {
        return result.entrySet().stream()
                .mapToLong(entry ->
                        (long) entry.getKey().getWinningAmount() * entry.getValue())
                .sum();
    }

    private double calculateProfitRate() {
        double rate = ((double) totalWinningAmount / purchaseAmount) * PERCENTAGE_MULTIPLIER;
        return Math.round(rate * ROUNDING_SCALE) / (double) ROUNDING_SCALE;
    }


    public double getProfitRate() {
        return profitRate;
    }
}

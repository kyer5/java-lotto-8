package lotto.domain.winning;

import lotto.domain.lotto.PurchaseAmount;

public class Profit {

    private static final int PERCENTAGE_MULTIPLIER = 100;
    private static final int ROUNDING_SCALE = 10;

    private final long totalWinningAmount;
    private final long purchaseAmount;
    private final double profitRate;

    public Profit(PurchaseAmount purchaseAmount, WinningResult winningResult) {
        this.totalWinningAmount = winningResult.totalWinningAmount();
        this.purchaseAmount = purchaseAmount.getAmount();
        this.profitRate = calculateProfitRate();
    }

    private double calculateProfitRate() {
        double rate = ((double) totalWinningAmount / purchaseAmount) * PERCENTAGE_MULTIPLIER;
        return Math.round(rate * ROUNDING_SCALE) / (double) ROUNDING_SCALE;
    }

    public double getProfitRate() {
        return profitRate;
    }
}

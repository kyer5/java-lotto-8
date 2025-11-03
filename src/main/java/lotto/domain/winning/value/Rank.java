package lotto.domain.winning.value;

import java.util.Arrays;

public enum Rank {

    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean isMatchBonusNumber;
    private final int winningAmount;

    Rank(int matchCount, boolean isMatchBonusNumber, int winningAmount) {
        this.matchCount = matchCount;
        this.isMatchBonusNumber = isMatchBonusNumber;
        this.winningAmount = winningAmount;
    }

    public static Rank find(int matchCount, boolean matchBonusNumber) {
        return Arrays.stream(values())
                .filter(rank ->
                        rank.matchCount == matchCount &&
                                (matchCount != 5 || rank.isMatchBonusNumber == matchBonusNumber)
                )
                .findFirst()
                .orElse(NONE);
    }
}
package lotto.domain.winning;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.winning.value.Rank;

public class WinningResult {

    private final Map<Rank, Integer> counts;

    public WinningResult() {
        this.counts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            counts.put(rank, 0);
        }
    }

    public void incrementCountOf(Rank rank) {
        counts.put(rank, counts.getOrDefault(rank, 0) + 1);
    }

    public int countOf(Rank rank) {
        return counts.getOrDefault(rank, 0);
    }

    public long totalWinningAmount() {
        long sum = 0L;
        for (Map.Entry<Rank, Integer> entry : counts.entrySet()) {
            sum += (long) entry.getKey().getWinningAmount() * entry.getValue();
        }
        return sum;
    }

    public List<Rank> ranksForStatistics() {
        return Rank.getWinningRanks();
    }

    Map<Rank, Integer> asMap() {
        return counts;
    }
}

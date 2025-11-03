package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.winning.value.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    @DisplayName("Rank별 개수에 따라 총 당첨 금액을 계산한다.")
    @Test
    void Rank별_개수에_따라_총_당첨_금액을_계산한다() {
        // given
        WinningResult winningResult = new WinningResult();
        winningResult.incrementCountOf(Rank.FIRST);
        winningResult.incrementCountOf(Rank.THIRD);
        winningResult.incrementCountOf(Rank.THIRD);
        winningResult.incrementCountOf(Rank.FIFTH);
        winningResult.incrementCountOf(Rank.FIFTH);
        winningResult.incrementCountOf(Rank.FIFTH);

        // when
        long totalWinningAmount = winningResult.totalWinningAmount();

        // then
        assertThat(totalWinningAmount).isEqualTo(2_003_015_000L);
    }
}

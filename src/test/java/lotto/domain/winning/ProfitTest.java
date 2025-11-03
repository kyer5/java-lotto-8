package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import lotto.domain.lotto.PurchaseAmount;
import lotto.domain.winning.value.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest  {

    @DisplayName("당첨 결과에 따라 총 당첨 금액을 계산한다.")
    @Test
    void 당첨_결과에_따라_총_당첨_금액을_계산한다() {
        // given
        Map<Rank, Integer> result = Map.of(
                Rank.FIRST, 1,
                Rank.THIRD, 2,
                Rank.FIFTH, 3
        );
        PurchaseAmount purchaseAmount = new PurchaseAmount("10000");

        // when
        Profit profit = new Profit(purchaseAmount, result);

        // then
        assertThat(profit).extracting("totalWinningAmount").isEqualTo(2_003_015_000L);
    }

    @DisplayName("구입 금액 대비 당첨금 비율로 수익률을 계산한다.")
    @Test
    void 구입_금액_대비_당첨금_비율로_수익률을_계산한다() {
        // given
        Map<Rank, Integer> result = Map.of(Rank.FOURTH, 1);
        PurchaseAmount purchaseAmount = new PurchaseAmount("100000");

        // when
        Profit profit = new Profit(purchaseAmount, result);

        // then
        assertThat(profit).extracting("profitRate").isEqualTo(50.0);
    }
}
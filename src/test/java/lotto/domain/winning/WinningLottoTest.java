package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.Map;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.winning.value.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {

    private WinningLotto testWinningLotto;

    @BeforeEach
    void setUp() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Bonus bonus = new Bonus("7");
        testWinningLotto = new WinningLotto(winning, bonus);
    }

    record Case(String desc, List<Integer> numbers, Rank expected) {}

    static List<Case> cases() {
        return List.of(
                new Case("6개 일치 → 1등", List.of(1, 2, 3, 4, 5, 6), Rank.FIRST),
                new Case("5개 일치 + 보너스 일치 → 2등", List.of(1, 2, 3, 4, 5, 7), Rank.SECOND),
                new Case("5개 일치 + 보너스 불일치 → 3등", List.of(1, 2, 3, 4, 5, 45), Rank.THIRD),
                new Case("4개 일치 → 4등", List.of(1, 2, 3, 4, 40, 41), Rank.FOURTH),
                new Case("3개 일치 → 5등", List.of(1, 2, 3, 40, 41, 42), Rank.FIFTH),
                new Case("2개 이하 일치 → 꽝", List.of(1, 2, 40, 41, 42, 43), Rank.NONE)
        );
    }

    @DisplayName("보너스 번호가 하나라도 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(numbers);
        Bonus bonus = new Bonus("6");

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinningLotto(winningLotto, bonus))
                .withMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("당첨 번호 일치 개수와 보너스 일치 여부에 따라 올바른 Rank를 반환한다.")
    @ParameterizedTest(name = "{index}. value = {0}")
    @MethodSource("cases")
    void 일치_개수와_보너스_일치_여부에_따라_올바른_Rank를_반환한다(Case testCase) {
        // given
        Lotto lotto = new Lotto(testCase.numbers());

        // when
        Rank result = testWinningLotto.matchRank(lotto);

        // then
        assertThat(result).isEqualTo(testCase.expected());
    }

    @DisplayName("당첨 결과에 따른 Rank별 개수를 올바르게 집계한다.")
    @Test
    void 당첨_결과의_Rank별_개수를_올바르게_집계한다() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 45)),
                new Lotto(List.of(1, 2, 3, 4, 6, 45)),
                new Lotto(List.of(1, 2, 3, 4, 40, 41)),
                new Lotto(List.of(1, 2, 3, 40, 41, 42)),
                new Lotto(List.of(1, 2, 40, 41, 42, 43))
        );
        LottoTicket lottoTicket = new LottoTicket(lottos);

        // when
        WinningResult winningResult = testWinningLotto.checkWinningResult(lottoTicket);

        // then
        assertThat(winningResult.asMap()).containsExactlyInAnyOrderEntriesOf(Map.of(
                Rank.FIRST, 1,
                Rank.SECOND, 1,
                Rank.THIRD, 2,
                Rank.FOURTH, 1,
                Rank.FIFTH, 1,
                Rank.NONE, 1
        ));

        assertThat(winningResult.countOf(Rank.FIRST)).isEqualTo(1);
        assertThat(winningResult.countOf(Rank.SECOND)).isEqualTo(1);
        assertThat(winningResult.countOf(Rank.THIRD)).isEqualTo(2);
        assertThat(winningResult.countOf(Rank.FOURTH)).isEqualTo(1);
        assertThat(winningResult.countOf(Rank.FIFTH)).isEqualTo(1);
        assertThat(winningResult.countOf(Rank.NONE)).isEqualTo(1);
    }
}

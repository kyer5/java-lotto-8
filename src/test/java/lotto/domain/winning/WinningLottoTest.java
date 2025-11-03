package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @DisplayName("보너스 번호가 하나라도 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        Lotto winningLotto = new Lotto(numbers);
        Bonus bonus = new Bonus("6");

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinningLotto(winningLotto, bonus))
                .withMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
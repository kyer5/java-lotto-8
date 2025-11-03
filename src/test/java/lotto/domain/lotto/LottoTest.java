package lotto.domain.lotto;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1보다 작은 값이 포함된 경우 예외가 발생한다.")
    @Test
    void 로또_번호가_최소값_미만이면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(0, 1, 2, 3, 4, 5);

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessageContaining("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");

    }

    @DisplayName("로또 번호가 45보다 큰 값이 포함된 경우 예외가 발생한다.")
    @Test
    void 로또_번호가_최대값_초과이면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);

        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessageContaining("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }
}

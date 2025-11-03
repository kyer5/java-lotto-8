package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusTest {

    @DisplayName("숫자(정수)가 아닌 문자를 입력하면 예외가 발생한다.")
    @ParameterizedTest(name = "{index}. value = {0}")
    @ValueSource(strings = {"a", " ", "", "@"})
    void 숫자가_아닌_문자열을_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Bonus(input))
                .withMessageContaining("[ERROR] 보너스 번호는 숫자(정수) 하나만 입력할 수 있습니다.");
    }

    @DisplayName("보너스 번호가 1보다 작은 경우 예외가 발생한다.")
    @ParameterizedTest(name = "{index}. value = {0}")
    @ValueSource(strings = {"-1", "0"})
    void 보너스_번호가_최소값_미만이면_예외가_발생한다(String input) {
        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Bonus(input))
                .withMessageContaining("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }
}
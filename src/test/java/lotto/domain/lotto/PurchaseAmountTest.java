package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또 구입 금액 테스트")
class PurchaseAmountTest {

    @DisplayName("숫자(정수) 이외의 다른 문자가 포함되어 있는 경우 예외가 발생한다. (공백 포함)")
    @ParameterizedTest(name = "{index}. value = {0}")
    @ValueSource(strings = {"8,000", "팔천원", "", " "})
    void 로또_구입_금액이_숫자_형태가_아니면_예외가_발생한다(String input) {
        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PurchaseAmount(input))
                .withMessageContaining("[ERROR] 로또 구입 금액은 숫자(정수)로만 입력할 수 있습니다.");
    }
}
package lotto.domain.lotto;

import static lotto.domain.LottoRuleConstant.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoRuleConstant.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoRuleConstant.MIN_LOTTO_NUMBER;

import java.util.List;

public class Lotto {

    private static final String LOTTO_NUMBER_INVALID_COUNT = "[ERROR] 로또 번호는 6개여야 합니다.";
    private static final String LOTTO_NUMBER_OUT_OF_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String LOTTO_NUMBER_DUPLICATED = "[ERROR] 로또 번호는 중복될 수 없습니다.";

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_INVALID_COUNT);
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean isInvalid = numbers.stream()
                .anyMatch(number -> number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER);

        if (isInvalid) {
            throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_RANGE);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATED);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}

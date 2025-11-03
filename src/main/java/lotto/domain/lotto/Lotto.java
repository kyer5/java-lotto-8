package lotto.domain.lotto;

import static lotto.domain.LottoRuleConstant.LOTTO_NUMBER_COUNT;

import java.util.List;

public class Lotto {

    private static final String LOTTO_NUMBER_INVALID_COUNT = "[ERROR] 로또 번호는 6개여야 합니다.";

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_INVALID_COUNT);
        }
    }
}

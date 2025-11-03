package lotto.domain.winning;

import static lotto.domain.LottoRuleConstant.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoRuleConstant.MIN_LOTTO_NUMBER;

public class Bonus {

    private static final String BONUS_NUMBER_NOT_NUMBER = "[ERROR] 보너스 번호는 숫자(정수) 하나만 입력할 수 있습니다.";
    private static final String BONUS_NUMBER_OUT_OF_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";

    private final int number;

    public Bonus(String inputNumber) {
        int number = parseBonusNumber(inputNumber);
        validateBonusNumberRange(number);
        this.number = number;
    }

    private int parseBonusNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BONUS_NUMBER_NOT_NUMBER);
        }
    }

    private void validateBonusNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(BONUS_NUMBER_OUT_OF_RANGE);
        }
    }

    public int getNumber() {
        return number;
    }
}

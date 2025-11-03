package lotto.domain.winning;

public class Bonus {

    private static final String BONUS_NUMBER_NOT_NUMBER = "[ERROR] 보너스 번호는 숫자(정수) 하나만 입력할 수 있습니다.";

    private final int number;

    public Bonus(String inputNumber) {
        int number = parseBonusNumber(inputNumber);
        this.number = number;
    }

    private int parseBonusNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BONUS_NUMBER_NOT_NUMBER);
        }
    }
}

package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class Input {

    private static final String DELIMITER = ",";
    private static final String PURCHASE_AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String WINNING_NUMBERS_INVALID_FORMAT = "[ERROR] 허용되지 않은 문자가 포함되어 있습니다. (옳은 입력 예시: 1,2,3,4,5,6)";

    public String readPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_INPUT_MESSAGE);
        return Console.readLine();
    }

    public String readWinningNumbers() {
        System.out.println("\n" + WINNING_NUMBERS_INPUT_MESSAGE);
        return Console.readLine();
    }
}

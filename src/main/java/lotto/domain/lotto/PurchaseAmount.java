package lotto.domain.lotto;

public class PurchaseAmount {

    private static final String PURCHASE_AMOUNT_NOT_NUMBER = "[ERROR] 로또 구입 금액은 숫자(정수)로만 입력할 수 있습니다.";

    private final int amount;

    public PurchaseAmount(String inputPurchaseAmount) {
        int amount = parsePurchaseAmount(inputPurchaseAmount);
        this.amount = amount;
    }

    private int parsePurchaseAmount(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_NOT_NUMBER);
        }
    }
}

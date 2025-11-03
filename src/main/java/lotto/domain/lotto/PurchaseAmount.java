package lotto.domain.lotto;

import static lotto.domain.LottoRuleConstant.MAX_PURCHASE_AMOUNT;
import static lotto.domain.LottoRuleConstant.MIN_PURCHASE_AMOUNT;
import static lotto.domain.LottoRuleConstant.PURCHASE_AMOUNT_UNIT;

public class PurchaseAmount {

    private static final String PURCHASE_AMOUNT_NOT_NUMBER = "[ERROR] 로또 구입 금액은 숫자(정수)로만 입력할 수 있습니다.";
    private static final String PURCHASE_AMOUNT_OUT_OF_RANGE = "[ERROR] 로또 구입은 최소 1,000원부터 최대 100,000원까지 가능합니다.";
    private static final String PURCHASE_AMOUNT_INVALID_UNIT = "[ERROR] 로또는 1,000원 단위로 구입할 수 있습니다.";

    private final int amount;

    public PurchaseAmount(String inputPurchaseAmount) {
        int amount = parsePurchaseAmount(inputPurchaseAmount);
        validatePurchaseAmountRange(amount);
        validatePurchaseAmountUnit(amount);
        this.amount = amount;
    }

    private int parsePurchaseAmount(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_NOT_NUMBER);
        }
    }

    private void validatePurchaseAmountRange(int amount) {
        if (amount < MIN_PURCHASE_AMOUNT || amount > MAX_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_OUT_OF_RANGE);
        }
    }

    private void validatePurchaseAmountUnit(int amount) {
        if (amount % PURCHASE_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_INVALID_UNIT);
        }
    }

    public int calculateLottoTicketQuantity() {
        return amount / PURCHASE_AMOUNT_UNIT;
    }

    public int getAmount() {
        return amount;
    }
}

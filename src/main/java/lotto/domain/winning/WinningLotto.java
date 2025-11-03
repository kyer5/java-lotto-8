package lotto.domain.winning;

import lotto.domain.lotto.Lotto;

public class WinningLotto {

    private static final String BONUS_NUMBER_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private final Lotto winningLotto;
    private final Bonus bonus;

    public WinningLotto(Lotto winningLotto, Bonus bonus) {
        this.winningLotto = winningLotto;
        validateBonusNumberDuplicate(bonus);
        this.bonus = bonus;
    }

    private void validateBonusNumberDuplicate(Bonus bonus) {
        if (winningLotto.getNumbers().contains(bonus.getNumber())) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE);
        }
    }
}

package lotto.domain.winning;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.winning.value.Rank;

public class WinningLotto {

    private static final String BONUS_NUMBER_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private final Lotto winningLotto;
    private final Bonus bonus;

    public WinningLotto(Lotto winningLotto, Bonus bonus) {
        this.winningLotto = winningLotto;
        validateBonusNumberDuplicate(bonus);
        this.bonus = bonus;
    }

    public WinningResult checkWinningResult(LottoTicket lottoTicket) {
        WinningResult result = new WinningResult();
        for (Lotto lotto : lottoTicket.getLottos()) {
            Rank rank = matchRank(lotto);
            result.incrementCountOf(rank);
        }
        return result;
    }

    public Rank matchRank(Lotto lotto) {
        int matchCount = countMatchingNumbers(lotto);
        boolean isMatchBonus = isMatchBonusNumber(lotto);
        return Rank.find(matchCount, isMatchBonus);
    }

    private int countMatchingNumbers(Lotto lotto) {
        List<Integer> winNums = winningLotto.getNumbers();
        return (int) lotto.getNumbers().stream()
                .filter(winNums::contains)
                .count();
    }

    private boolean isMatchBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(bonus.getNumber());
    }

    private void validateBonusNumberDuplicate(Bonus bonus) {
        if (winningLotto.getNumbers().contains(bonus.getNumber())) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE);
        }
    }
}

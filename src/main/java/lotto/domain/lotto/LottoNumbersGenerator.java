package lotto.domain.lotto;

import static lotto.domain.LottoRuleConstant.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoRuleConstant.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoRuleConstant.MIN_LOTTO_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumbersGenerator {

    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_NUMBER_COUNT);
    }
}

package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoIssuerTest {

    @DisplayName("구입 금액의 맞는 개수의 로또를 발행한다.")
    @Test
    void 구입_금액에_맞는_개수의_로또를_발행한다() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount("8000");
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator();
        LottoIssuer lottoIssuer = new LottoIssuer(purchaseAmount, lottoNumbersGenerator);

        // when
        LottoTicket lottoTicket = lottoIssuer.generateLottoTicket();

        // then
        assertThat(lottoTicket.getCount()).isEqualTo(8);
    }
}
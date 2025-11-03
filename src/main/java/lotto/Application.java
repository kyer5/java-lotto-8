package lotto;

import lotto.controller.LottoController;
import lotto.domain.lotto.LottoNumbersGenerator;
import lotto.view.Input;
import lotto.view.Output;

public class Application {
    public static void main(String[] args) {
        Input input = new Input();
        Output output = new Output();
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator();
        LottoController lottoController = new LottoController(input, output, lottoNumbersGenerator);
        lottoController.play();
    }
}

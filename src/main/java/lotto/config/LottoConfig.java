package lotto.config;

import lotto.controller.LottoController;
import lotto.domain.LottoFactory;
import lotto.domain.RandomNumberGenerator;
import lotto.view.ConsoleInput;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoConfig {

    public static LottoController getLottoController() {
        return new LottoController(getInputView(), getOutView());
    }

    public static InputView getInputView() {
        return new InputView(new ConsoleInput());
    }

    public static OutputView getOutView() {
        return new OutputView();
    }

    public static LottoFactory getLottoFactory(int money) {
        return LottoFactory.create(new RandomNumberGenerator(), money);
    }
}

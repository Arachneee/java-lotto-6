package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.config.LottoConfig;
import lotto.domain.AnswerLotto;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.Result;
import lotto.dto.ResultsDto;
import lotto.exception.LottoException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final LottoFactory lottoFactory = makeLottoFactory();
        outputView.printLottoNumbers(lottoFactory.getLottoNumbers());

        final AnswerLotto answerLotto = makeAnswerLotto(getMainLotto());

        final Result result = lottoFactory.calculateResult(answerLotto);

        outputView.printResult(ResultsDto.of(result));
        outputView.printRateOfReturn(result.calculateRateToReturn());

        Console.close();
    }

    private LottoFactory makeLottoFactory() {
        while (true) {
            try {
                return LottoConfig.getLottoFactory(inputView.enterMoney());
            } catch (LottoException lottoException) {
                outputView.printError(lottoException.getMessage());
            }
        }
    }

    private Lotto getMainLotto() {
        while (true) {
            try {
                return new Lotto(inputView.enterLotto());
            } catch (LottoException lottoException) {
                outputView.printError(lottoException.getMessage());
            }
        }
    }

    private AnswerLotto makeAnswerLotto(final Lotto mainLotto) {
        while (true) {
            try {
                return AnswerLotto.create(mainLotto, inputView.enterBonusNumber());
            } catch (LottoException lottoException) {
                outputView.printError(lottoException.getMessage());
            }
        }
    }

}

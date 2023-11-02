package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.RandomNumberGenerator;
import lotto.domain.Result;
import lotto.dto.ResultsDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int money = inputView.enterMoney();
        LottoFactory lottoFactory = LottoFactory.create(new RandomNumberGenerator(), money);

        outputView.printLottoNumbers(lottoFactory.getLottoNumbers());

        Lotto answerLotto = new Lotto(inputView.enterLotto());
        int bonusNumber = inputView.enterBonusNumber();

        Result result = Result.of(lottoFactory.calculateResult(answerLotto, bonusNumber));

        float rateOfReturn = result.calculateRate(money);

        outputView.printResult(ResultsDto.of(result));
        outputView.printRateOfReturn(rateOfReturn);
    }
}

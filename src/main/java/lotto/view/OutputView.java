package lotto.view;

import java.util.Comparator;
import java.util.List;
import lotto.dto.ResultDto;
import lotto.dto.ResultsDto;

public class OutputView {

    private static final String LOTTO_BUY_FORMAT = "\n%d개를 구매했습니다.\n";
    private static final String LOTTO_RESULT_HEADER = "당첨 통계\n---";
    private static final String LOTTO_RESULT_FORMAT = "%d개 일치%s (%,d원) - %d개\n";
    private static final String HAS_BONUS = ", 보너스 볼 일치";
    private static final String NONE_BONUS = "";
    private static final String RATE_OF_RETURN = "총 수익률은 %,.1f%%입니다.\n";
    public void printLottoNumbers(final List<List<Integer>> lottoNumbers) {
        System.out.printf(LOTTO_BUY_FORMAT, lottoNumbers.size());

        lottoNumbers.forEach(System.out::println);
    }

    public void printResult(final ResultsDto resultsDto) {
        System.out.println(LOTTO_RESULT_HEADER);

        final List<ResultDto> results = resultsDto.getResultsDto();

        results.forEach(result -> System.out.printf(LOTTO_RESULT_FORMAT,
                result.getSameNumberCount(),
                getBonus(result),
                result.getReward(),
                result.getCount()));
    }

    private String getBonus(final ResultDto result) {
        if (result.isHasBonus()) {
            return HAS_BONUS;
        }

        return NONE_BONUS;
    }

    public void printRateOfReturn(final float rateOfReturn) {
        System.out.printf(RATE_OF_RETURN, rateOfReturn);
    }

    public void printError(final String message) {
        System.out.println(message);
    }
}

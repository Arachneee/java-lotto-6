package lotto.domain;

import static lotto.constant.LottoConstant.COUNT;
import static lotto.exception.ErrorMessage.DUPLICATE_NUMBER;
import static lotto.exception.ErrorMessage.WRONG_SIZE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lotto.constant.LottoConstant;
import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(final List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private void validateSize(final List<Integer> numbers) {
        if (numbers.size() != COUNT.getValue()) {
            throw LottoException.of(WRONG_SIZE);
        }
    }

    private void validateDuplicate(final List<Integer> numbers) {
        if (isDuplicateNumber(numbers)) {
            throw LottoException.of(DUPLICATE_NUMBER);
        }
    }

    private static boolean isDuplicateNumber(final List<Integer> numbers) {
        return numbers.size() != Set.copyOf(numbers).size();
    }

    private void validateRange(final List<Integer> numbers) {
        if (isWrongRange(numbers)) {
            throw LottoException.of(ErrorMessage.WRONG_RANGE);
        }
    }

    private void validateRange(final int bonusNumber) {
        if (isWrongRange(bonusNumber)) {
            throw LottoException.of(ErrorMessage.WRONG_RANGE);
        }
    }

    private static boolean isWrongRange(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(Lotto::isWrongRange);
    }

    private static boolean isWrongRange(final Integer number) {
        return number < LottoConstant.MIN_NUMBER.getValue() ||
                number > LottoConstant.MAX_NUMBER.getValue();
    }

    public void validateBonusNumber(final int bonusNumber) {
        validateRange(bonusNumber);

        if (numbers.contains(bonusNumber)) {
            throw LottoException.of(DUPLICATE_NUMBER);
        }

    }

    public int countSameNumber(final Lotto lotto) {
        return lotto.countSameNumber(numbers);
    }

    private int countSameNumber(final List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public boolean contains(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}

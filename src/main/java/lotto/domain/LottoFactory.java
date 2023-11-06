package lotto.domain;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.constant.Rank;

public class LottoFactory {

    private final List<Lotto> lottos;

    private LottoFactory(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static LottoFactory of(final NumberGenerator numberGenerator, final Money money) {
        return new LottoFactory(createLottos(numberGenerator, money));
    }

    private static List<Lotto> createLottos(final NumberGenerator numberGenerator,
            final Money money) {
        return Stream.generate(numberGenerator::generate)
                .limit(money.calculateLottoCount())
                .map(Lotto::new)
                .toList();
    }

    public Result calculateResult(final AnswerLotto answerLotto) {
        return Result.from(lottos.stream()
                .collect(Collectors.toMap(answerLotto::getRank,
                        value -> 1L,
                        Long::sum,
                        getEnumMapSupplier())));

    }

    private Supplier<EnumMap<Rank, Long>> getEnumMapSupplier() {
        return () -> {
            EnumMap<Rank, Long> enumMap = new EnumMap<>(Rank.class);
            EnumSet.allOf(Rank.class)
                    .forEach(rank -> enumMap.put(rank, 0L));

            return enumMap;
        };
    }

    public List<String> getLottoNumbers() {
        return lottos.stream()
                .map(Lotto::toString)
                .toList();
    }

}

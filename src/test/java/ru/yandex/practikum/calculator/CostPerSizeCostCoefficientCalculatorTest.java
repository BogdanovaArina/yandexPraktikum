package ru.yandex.practikum.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yandex.practikum.model.Condition;
import ru.yandex.practikum.model.CostCoefficient;
import ru.yandex.practikum.model.Fragility;
import ru.yandex.practikum.model.Load;

import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practikum.model.Size.BIG;
import static ru.yandex.practikum.model.Size.SMALL;

class CostPerSizeCostCoefficientCalculatorTest {

    private CostPerSizeCostCoefficientCalculator costPerSizeCalculator;

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(new Condition(10, BIG.toString(), Fragility.NORMAL.toString(), Load.NORMAL.toString()),
                        new CostCoefficient(valueOf(200), ONE)),
                Arguments.of(new Condition(10, SMALL.toString(), Fragility.NORMAL.toString(), Load.NORMAL.toString()),
                        new CostCoefficient(valueOf(100), ONE)));
    }

    @BeforeEach
    void setUp() {
        costPerSizeCalculator = new CostPerSizeCostCoefficientCalculator();
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void shouldCalculateCostPerSize(Condition condition, CostCoefficient expectedCostCoefficients) {

        //when
        CostCoefficient resultCostCoefficients = costPerSizeCalculator.calculateCost(condition);

        //then
        assertEquals(expectedCostCoefficients, resultCostCoefficients);
    }
}

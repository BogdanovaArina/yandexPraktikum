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

class CostPerDistanceCostCoefficientCalculatorTest {
    private CostPerDistanceCostCoefficientCalculator costPerDistanceCalculator;

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(new Condition(100, BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(300), ONE)),
                Arguments.of(new Condition(30.0001, BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(300), ONE)),
                Arguments.of(new Condition(30, BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(200), ONE)),
                Arguments.of(new Condition(15, BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(200), ONE)),
                Arguments.of(new Condition(10.0001, BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(200), ONE)),
                Arguments.of(new Condition(10, BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(100), ONE)),
                Arguments.of(new Condition(5, BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(100), ONE)),
                Arguments.of(new Condition(2.0001, BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(100), ONE)),
                Arguments.of(new Condition(2, BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(50), ONE)),
                Arguments.of(new Condition(1.0001, BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(50), ONE)));
    }

    @BeforeEach
    void setUp() {
        costPerDistanceCalculator = new CostPerDistanceCostCoefficientCalculator();
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void shouldCalculateCostPerDistance(Condition condition, CostCoefficient expectedCostCoefficients) {
        //when
        CostCoefficient resultCostCoefficients = costPerDistanceCalculator.calculateCost(condition);

        //then
        assertEquals(expectedCostCoefficients, resultCostCoefficients);
    }


}
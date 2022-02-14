package ru.yandex.practikum.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yandex.practikum.model.Condition;
import ru.yandex.practikum.model.CostCoefficient;
import ru.yandex.practikum.model.Fragility;

import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;
import static java.math.BigDecimal.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practikum.model.Load.*;
import static ru.yandex.practikum.model.Size.SMALL;

class CostPerDeliveryWorkloadCostCoefficientCalculatorTest {
    private CostPerDeliveryWorkloadCostCoefficientCalculator costPerDeliveryWorkloadCalculator;

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(new Condition(10, SMALL.toString(), Fragility.NORMAL.toString(), VERY_HIGH.toString()),
                        new CostCoefficient(ZERO, valueOf(1.6))),
                Arguments.of(new Condition(10, SMALL.toString(), Fragility.NOT_FRAGILE.toString(), HIGH.toString()),
                        new CostCoefficient(ZERO, valueOf(1.4))),
                Arguments.of(new Condition(10, SMALL.toString(), Fragility.NORMAL.toString(), HEIGHTENED.toString()),
                        new CostCoefficient(ZERO, valueOf(1.2))),
                Arguments.of(new Condition(10, SMALL.toString(), Fragility.NORMAL.toString(), NORMAL.toString()),
                        new CostCoefficient(ZERO, ONE)),
                Arguments.of(new Condition(10, SMALL.toString(), Fragility.NORMAL.toString(), LOW.toString()),
                        new CostCoefficient(ZERO, ONE)));

    }

    @BeforeEach
    void setUp() {
        costPerDeliveryWorkloadCalculator = new CostPerDeliveryWorkloadCostCoefficientCalculator();
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void shouldCalculateCostPerDeliveryWorkload(Condition condition, CostCoefficient expectedCostCoefficients) {
        //when
        CostCoefficient resultCostCoefficients = costPerDeliveryWorkloadCalculator.calculateCost(condition);

        //then
        assertEquals(expectedCostCoefficients, resultCostCoefficients);
    }


}
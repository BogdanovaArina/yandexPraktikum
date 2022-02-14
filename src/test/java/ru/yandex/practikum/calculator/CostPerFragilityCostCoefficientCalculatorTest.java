package ru.yandex.practikum.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yandex.practikum.model.*;

import java.util.stream.Stream;

import static java.math.BigDecimal.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CostPerFragilityCostCoefficientCalculatorTest {

    private CostPerFragilityCostCoefficientCalculator costPerFragilityCalculator;

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(new Condition(30, Size.BIG.toString(), Fragility.FRAGILE.toString(), Load.LOW.toString()),
                        new CostCoefficient(valueOf(300), ONE)),
                Arguments.of(new Condition(10, Size.BIG.toString(), Fragility.NOT_FRAGILE.toString(), Load.LOW.toString()),
                        new CostCoefficient(ZERO, ONE)),
                Arguments.of(new Condition(10, Size.BIG.toString(), Fragility.NORMAL.toString(), Load.LOW.toString()),
                        new CostCoefficient(ZERO, ONE)));
    }

    @BeforeEach
    void setUp() {
        costPerFragilityCalculator = new CostPerFragilityCostCoefficientCalculator();
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void shouldCalculateCostPerFragility(Condition condition, CostCoefficient expectedCostCoefficients) {
        //when
        CostCoefficient resultCostCoefficients = costPerFragilityCalculator.calculateCost(condition);

        //then
        assertEquals(expectedCostCoefficients, resultCostCoefficients);
    }

}
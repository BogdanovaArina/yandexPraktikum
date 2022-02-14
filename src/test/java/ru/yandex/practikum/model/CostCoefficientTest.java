package ru.yandex.practikum.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CostCoefficientTest {

    private static Stream<Arguments> provideArgumentsForAddTest() {
        return Stream.of(
                Arguments.of(new CostCoefficient(BigDecimal.valueOf(200.1), BigDecimal.valueOf(3)), new CostCoefficient(BigDecimal.valueOf(250.23), BigDecimal.valueOf(6.6))),
                Arguments.of(new CostCoefficient(BigDecimal.valueOf(-300), BigDecimal.valueOf(0)), new CostCoefficient(BigDecimal.valueOf(-249.87), BigDecimal.valueOf(0.0))),
                Arguments.of(new CostCoefficient(BigDecimal.valueOf(0), BigDecimal.valueOf(-4)), new CostCoefficient(BigDecimal.valueOf(50.13), BigDecimal.valueOf(-8.8))));

    }

    private static Stream<Arguments> provideArgumentsForReturnCostTest() {
        return Stream.of(
                Arguments.of(new CostCoefficient(BigDecimal.valueOf(57.12), BigDecimal.valueOf(2.2)), BigDecimal.valueOf(125.664)),
                Arguments.of(new CostCoefficient(BigDecimal.valueOf(-500), BigDecimal.valueOf(11.5)), BigDecimal.valueOf(-5750.0)),
                Arguments.of(new CostCoefficient(BigDecimal.ZERO, BigDecimal.valueOf(10.6)), BigDecimal.valueOf(0.0)),
                Arguments.of(new CostCoefficient(BigDecimal.valueOf(100), BigDecimal.ZERO), BigDecimal.ZERO),
                Arguments.of(new CostCoefficient(BigDecimal.valueOf(200), BigDecimal.valueOf(-1.6)), BigDecimal.valueOf(-320.0)));

    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForAddTest")
    void shouldAdd(CostCoefficient costCoefficientForAdding, CostCoefficient expectedCostCoefficient) {
        //given
        CostCoefficient initialCostCoefficient = new CostCoefficient(BigDecimal.valueOf(50.13), BigDecimal.valueOf(2.2));

        //when
        CostCoefficient actualCostCoefficient = initialCostCoefficient.add(costCoefficientForAdding);

        //then
        assertEquals(expectedCostCoefficient, actualCostCoefficient);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForReturnCostTest")
    void shouldReturnCostValue(CostCoefficient costCoefficient, BigDecimal expectedCost) {
        //when
        BigDecimal actualCostValue = costCoefficient.getCostValue();

        //then
        assertEquals(expectedCost, actualCostValue);
    }

}
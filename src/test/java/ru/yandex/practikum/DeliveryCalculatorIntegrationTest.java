package ru.yandex.practikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practikum.calculator.*;
import ru.yandex.practikum.model.Cost;
import ru.yandex.practikum.model.Currency;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryCalculatorIntegrationTest {
    private static final BigDecimal MINIMUM_PRICE = BigDecimal.valueOf(400);
    private DeliveryCalculator deliveryCalculator;

    @BeforeEach
    void setUp() {
        List<CostCoefficientCalculator> costCoefficientCalculators = List.of(
                new CostPerFragilityCostCoefficientCalculator(),
                new CostPerSizeCostCoefficientCalculator(),
                new CostPerDeliveryWorkloadCostCoefficientCalculator(),
                new CostPerDistanceCostCoefficientCalculator()
        );
        deliveryCalculator = new DeliveryCalculator(costCoefficientCalculators);
    }

    @Test
    void shouldCalculateMinimumPrice() {
        //given
        Cost expectedCost = Cost.builder()
                .currency(Currency.RUB)
                .value(MINIMUM_PRICE)
                .build();

        //when
        Cost resultCost = deliveryCalculator.calculateDeliverySum(2, "small", "normal", "low");

        //then
        assertEquals(expectedCost, resultCost);
    }


    @Test
    void shouldCalculatePrice() {
        //given
        Cost expectedCost = Cost.builder()
                .currency(Currency.RUB)
                .value(BigDecimal.valueOf(800.0))
                .build();

        //when
        Cost resultCost = deliveryCalculator.calculateDeliverySum(200, "Big", "Not Fragile", "Very high");

        //then
        assertEquals(expectedCost, resultCost);
    }
}
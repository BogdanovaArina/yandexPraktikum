package ru.yandex.practikum.calculator;

import ru.yandex.practikum.model.Condition;
import ru.yandex.practikum.model.CostCoefficient;
import ru.yandex.practikum.model.Load;

import java.math.BigDecimal;
import java.util.Map;

import static java.math.BigDecimal.ZERO;

public class CostPerDeliveryWorkloadCostCoefficientCalculator implements CostCoefficientCalculator {

    private static final CostCoefficient DEFAULT_COST_COEFFICIENT = new CostCoefficient(BigDecimal.ZERO, BigDecimal.ONE);
    private static final Map<Load, CostCoefficient> COSTS_PER_DELIVERY_LOAD = Map.of(
            Load.VERY_HIGH, new CostCoefficient(ZERO, BigDecimal.valueOf(1.6)),
            Load.HIGH, new CostCoefficient(ZERO, BigDecimal.valueOf(1.4)),
            Load.HEIGHTENED, new CostCoefficient(ZERO, BigDecimal.valueOf(1.2))
    );

    @Override
    public CostCoefficient calculateCost(Condition condition) {
        Load load = condition.getLoad();
        return COSTS_PER_DELIVERY_LOAD.getOrDefault(load, DEFAULT_COST_COEFFICIENT);
    }
}


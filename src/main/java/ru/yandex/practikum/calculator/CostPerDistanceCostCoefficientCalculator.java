package ru.yandex.practikum.calculator;

import ru.yandex.practikum.model.Condition;
import ru.yandex.practikum.model.CostCoefficient;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;

public class CostPerDistanceCostCoefficientCalculator implements CostCoefficientCalculator {
    @Override
    public CostCoefficient calculateCost(Condition condition) {
        double distance = condition.getDistance();
        if (distance > 30) {
            return new CostCoefficient(BigDecimal.valueOf(300), ONE);
        } else if (10 < distance) {
            return new CostCoefficient(BigDecimal.valueOf(200), ONE);
        } else if (2 < distance) {
            return new CostCoefficient(BigDecimal.valueOf(100), ONE);
        } else {
            return new CostCoefficient(BigDecimal.valueOf(50), ONE);
        }
    }
}

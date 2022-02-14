package ru.yandex.practikum.calculator;

import ru.yandex.practikum.model.Condition;
import ru.yandex.practikum.model.CostCoefficient;
import ru.yandex.practikum.model.Fragility;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static ru.yandex.practikum.model.Fragility.FRAGILE;

public class CostPerFragilityCostCoefficientCalculator implements CostCoefficientCalculator {

    @Override
    public CostCoefficient calculateCost(Condition condition) {
        Fragility fragility = condition.getFragility();
        if (FRAGILE.equals(fragility)) {
            return new CostCoefficient(BigDecimal.valueOf(300), ONE);
        } else {
            return new CostCoefficient(ZERO, ONE);
        }
    }
}

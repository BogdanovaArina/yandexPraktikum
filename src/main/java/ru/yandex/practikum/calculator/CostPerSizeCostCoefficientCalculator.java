package ru.yandex.practikum.calculator;

import ru.yandex.practikum.model.Condition;
import ru.yandex.practikum.model.CostCoefficient;
import ru.yandex.practikum.model.Size;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static ru.yandex.practikum.model.Size.BIG;

public class CostPerSizeCostCoefficientCalculator implements CostCoefficientCalculator {

    @Override
    public CostCoefficient calculateCost(Condition condition) {
        Size size = condition.getSize();
        if (BIG.equals(size)) {
            return new CostCoefficient(BigDecimal.valueOf(200), ONE);
        } else {
            return new CostCoefficient(BigDecimal.valueOf(100), ONE);
        }
    }
}

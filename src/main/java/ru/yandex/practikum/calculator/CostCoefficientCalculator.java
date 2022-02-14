package ru.yandex.practikum.calculator;

import ru.yandex.practikum.model.Condition;
import ru.yandex.practikum.model.CostCoefficient;

public interface CostCoefficientCalculator {
    CostCoefficient calculateCost(Condition condition);
}

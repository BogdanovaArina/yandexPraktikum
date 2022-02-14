package ru.yandex.practikum;

import ru.yandex.practikum.calculator.CostCoefficientCalculator;
import ru.yandex.practikum.model.Condition;
import ru.yandex.practikum.model.Cost;
import ru.yandex.practikum.model.CostCoefficient;
import ru.yandex.practikum.model.Currency;

import java.math.BigDecimal;
import java.util.List;

import static ru.yandex.practikum.model.Currency.RUB;

public class DeliveryCalculator {
    private static final BigDecimal MIN_COST = BigDecimal.valueOf(400);
    private final List<CostCoefficientCalculator> costCoefficientCalculators;

    public DeliveryCalculator(List<CostCoefficientCalculator> costCoefficientCalculators) {
        this.costCoefficientCalculators = costCoefficientCalculators;
    }

    public Cost calculateDeliverySum(int distance, String size, String fragility, String load) {
        Condition condition = new Condition(distance, size, fragility, load);

        CostCoefficient result = CostCoefficient.getDefault();

        for (CostCoefficientCalculator costCoefficientCalculator : costCoefficientCalculators) {
            CostCoefficient costCoefficients = costCoefficientCalculator.calculateCost(condition);
            result = result.add(costCoefficients);
        }

        BigDecimal cost = result.getCostValue();
        if (cost.compareTo(MIN_COST) > 0) {
            return Cost.builder().value(cost).currency(RUB).build();
        } else {
            return Cost.builder().value(MIN_COST).currency(RUB).build();
        }
    }
}

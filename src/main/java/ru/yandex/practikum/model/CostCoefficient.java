package ru.yandex.practikum.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CostCoefficient {
    private final BigDecimal summand;
    private final BigDecimal multiplier;

    public static CostCoefficient getDefault() {
        return new CostCoefficient(BigDecimal.ZERO, BigDecimal.ONE);
    }

    public CostCoefficient add(CostCoefficient costCoefficient) {
        return new CostCoefficient(summand.add(costCoefficient.getSummand()),
                multiplier.multiply(costCoefficient.getMultiplier()));
    }

    public BigDecimal getCostValue() {
        return summand.multiply(multiplier);
    }
}

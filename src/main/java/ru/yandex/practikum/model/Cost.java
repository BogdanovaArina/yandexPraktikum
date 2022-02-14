package ru.yandex.practikum.model;


import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class Cost {
    private final BigDecimal value;
    private final Currency currency;
}

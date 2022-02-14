package ru.yandex.practikum.model;

import lombok.Value;
import org.apache.commons.lang3.EnumUtils;

import java.util.Arrays;
import java.util.Optional;


@Value
public class Condition {

    private final double distance;
    private final Size size;
    private final Fragility fragility;
    private final Load load;

    public Condition(double distance, String size, String fragility, String load) {
        if (("FRAGILE".equals(fragility)) && (distance > 30)) {
            throw new IllegalArgumentException("Fragile shipments can be sent only within 30km");
        }

        if ((distance < 0) || (distance == 0)) {
            throw new IllegalArgumentException("Distance should be greater than 0km");
        }

        this.distance = distance;

        this.size = Optional.ofNullable(size)
                .map(mbSize -> mbSize.replace(" ", "_"))
                .map(mbSize -> EnumUtils.getEnumIgnoreCase(Size.class, mbSize))
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s value can not be converted. Supported values are %s", size, Arrays.toString(Size.values()))));

        this.fragility = Optional.ofNullable(fragility)
                .map(mbFragility -> mbFragility.replace(" ", "_"))
                .map(mbFragility -> EnumUtils.getEnumIgnoreCase(Fragility.class, mbFragility))
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s value can not be converted. Supported values are %s", fragility, Arrays.toString(Fragility.values()))));


        this.load = Optional.ofNullable(load)
                .map(mbLoad -> mbLoad.replace(" ", "_"))
                .map(mbLoad -> EnumUtils.getEnumIgnoreCase(Load.class, mbLoad))
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s value can not be converted. Supported values are %s", load, Arrays.toString(Load.values()))));
    }
}

package ru.yandex.practikum.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.yandex.practikum.model.Fragility.FRAGILE;
import static ru.yandex.practikum.model.Fragility.NOT_FRAGILE;
import static ru.yandex.practikum.model.Load.NORMAL;
import static ru.yandex.practikum.model.Size.BIG;

class ConditionTest {

    private static Stream<Arguments> provideArgumentsForExceptionTests() {
        return Stream.of(
                //distance exceptions
                Arguments.of(30.5, BIG.toString(), FRAGILE.toString(), NORMAL.toString(), "Fragile shipments can be sent only within 30km"),
                Arguments.of(0, BIG.toString(), FRAGILE.toString(), NORMAL.toString(), "Distance should be greater than 0km"),
                Arguments.of(-10.1, BIG.toString(), FRAGILE.toString(), NORMAL.toString(), "Distance should be greater than 0km"),

                //size exceptions
                Arguments.of(10, "medium", FRAGILE.toString(), NORMAL.toString(), "medium value can not be converted. Supported values are [BIG, SMALL]"),
                Arguments.of(10, "", FRAGILE.toString(), NORMAL.toString(), " value can not be converted. Supported values are [BIG, SMALL]"),
                Arguments.of(10, null, FRAGILE.toString(), NORMAL.toString(), "null value can not be converted. Supported values are [BIG, SMALL]"),

                //fragility exceptions
                Arguments.of(10, BIG.toString(), "soft", NORMAL.toString(), "soft value can not be converted. Supported values are [FRAGILE, NOT_FRAGILE, NORMAL]"),
                Arguments.of(10, BIG.toString(), "", NORMAL.toString(), " value can not be converted. Supported values are [FRAGILE, NOT_FRAGILE, NORMAL]"),
                 Arguments.of(10, BIG.toString(), null, NORMAL.toString(), "null value can not be converted. Supported values are [FRAGILE, NOT_FRAGILE, NORMAL]"),

                //workload exceptions
                Arguments.of(10, BIG.toString(), FRAGILE.toString(), "limited", "limited value can not be converted. Supported values are [VERY_HIGH, HIGH, HEIGHTENED, NORMAL, LOW]"),
                Arguments.of(10, BIG.toString(), FRAGILE.toString(), "", " value can not be converted. Supported values are [VERY_HIGH, HIGH, HEIGHTENED, NORMAL, LOW]"),
                Arguments.of(10, BIG.toString(), FRAGILE.toString(), null, "null value can not be converted. Supported values are [VERY_HIGH, HIGH, HEIGHTENED, NORMAL, LOW]"));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForExceptionTests")
    void shouldBeConditionException(double distance, String size, String fragility, String load, String expectedMessage) {

        //when
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Condition(distance, size, fragility, load)
        );

        String message = illegalArgumentException.getMessage();

        //then
        assertEquals(expectedMessage, message);
    }
}
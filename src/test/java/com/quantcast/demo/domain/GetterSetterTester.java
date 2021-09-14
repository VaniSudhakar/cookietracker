package com.quantcast.demo.domain;


import org.joor.Reflect;

import java.util.Locale;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GetterSetterTester<T> implements BiConsumer {
    private final Reflect r;

    public GetterSetterTester(Class<?> clazz) {
        this.r = Reflect.on(clazz).create();
    }

    public void accept(String p, Object v) {
        final String property =
                p.substring(0, 1).toUpperCase(Locale.ENGLISH) + p.substring(1);
        final String verbSet = "set";
        final String verbGet = v instanceof Boolean ? "is" : "get";
        try {
            assertEquals(v,
                    r.call(verbSet + property, v).call(verbGet + property).get());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Override
    public void accept(Object o, Object o2) {

    }

    @Override
    public BiConsumer andThen(BiConsumer after) {
        return BiConsumer.super.andThen(after);
    }
}
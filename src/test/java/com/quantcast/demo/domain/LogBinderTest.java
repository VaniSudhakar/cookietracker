package com.quantcast.demo.domain;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class LogBinderTest {

    @Test
    @Ignore //final class. no additional runs
    public void beanTest() {
        final Map<String, Object> values = new HashMap<>();
        values.put("cookie", "foobar");
        values.put("timestamp", "2018-12-09'T'10:30:20+00:00");
        values.forEach(new GetterSetterTester<>(LogBinder.class));
        LogBinder lb = new LogBinder();
        lb.setCookie("test");
        lb.setTimestamp("2018-12-09'T'10:30:20+00:00");
        Assertions.assertEquals("demo.LogBinder{cookie='test', " +
                "timestamp='2018-12-09'T'10:30:20+00:00'}", lb.toString());
    }

}
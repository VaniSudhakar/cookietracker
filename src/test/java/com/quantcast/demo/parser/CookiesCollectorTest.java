package com.quantcast.demo.parser;

import com.quantcast.demo.domain.CookieFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CookiesCollectorTest {

    @Test
    public void testCollectorMethods() {
        CookiesCollector cookiesCollector = new CookiesCollector();
        CookiesMap cookiesMap = cookiesCollector.supplier().get();
        CookieFormat key = new CookieFormat("cookie", "2018-12-09T10:30:20+00:00");
        cookiesMap.put(key, 2);
        Assertions.assertNotNull(cookiesCollector.accumulator());
        Assertions.assertNotNull(cookiesCollector.combiner());
        Assertions.assertNotNull(cookiesCollector.finisher());
    }

}
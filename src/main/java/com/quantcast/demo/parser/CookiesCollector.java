package com.quantcast.demo.parser;

import com.quantcast.demo.converter.CookieFormatAdapter;
import com.quantcast.demo.domain.LogBinder;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * <code>CookiesCollector</code> custom collector class for streaming
 * filtered custom collection created.
 */
public class CookiesCollector implements Collector<LogBinder, CookiesMap, Map> {


    @Override
    public Supplier<CookiesMap> supplier() {
        return CookiesMap::new;
    }

    @Override
    public BiConsumer<CookiesMap, LogBinder> accumulator() {
        return (map, logBinder) -> map.put(new CookieFormatAdapter(logBinder).convert(), 1);
    }

    @Override
    public BinaryOperator<CookiesMap> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    @Override
    public Function<CookiesMap, Map> finisher() {
        return finalMap -> Collections.unmodifiableMap(finalMap);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}

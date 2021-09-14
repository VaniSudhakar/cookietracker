package com.quantcast.demo.parser;

import com.quantcast.demo.domain.CookieFormat;

import java.util.HashMap;

/**
 * <code>CookiesMap</code> is an extension of Hashmap to just increment the
 * count of
 * occurrence of a key by 1, instead of replacing the value.
 * <p>
 * Could be extended to not insert null. But, ideally null should never be
 * incoming.
 */
public class CookiesMap extends HashMap<CookieFormat, Integer> {

    @Override
    public Integer put(CookieFormat key, Integer value) {
        int count = 1;
        if (super.containsKey(key)) {
            count = super.get(key);
            count++;
        }
        return super.put(key, count);
    }
}

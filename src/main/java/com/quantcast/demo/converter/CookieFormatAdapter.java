package com.quantcast.demo.converter;

import com.quantcast.demo.domain.CookieFormat;
import com.quantcast.demo.domain.LogBinder;

/**
 * <code>CookieFormatAdapter</code> is similar to an Adapter, but adapts
 * without attempting extension of the final class.
 */
public class CookieFormatAdapter {

    private final LogBinder logBinder;

    public CookieFormatAdapter(LogBinder logBinder) {
        this.logBinder = logBinder;
    }

    /**
     * Convert the logbinder to cookieformat. Added for better separation
     * while trying to work with opencsv.
     * Doesn't serve additional purposes as of now.
     *
     * @return CookieFormat
     */
    public CookieFormat convert() {
        return new CookieFormat(logBinder.getCookie(),
                logBinder.getTimestamp());
    }
}

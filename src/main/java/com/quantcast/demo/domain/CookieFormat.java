package com.quantcast.demo.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <code>CookieFormat</code> is the immutable key for Custom HashMap
 */
public final class CookieFormat implements Comparable<CookieFormat> {

    public static final String PATTERN = "yyyy-MM-dd" +
            "'T'HH:mm:ssXXX";
    private final String cookie;
    private final LocalDateTime localDate;

    public CookieFormat(String cookie, String timestamp) {
        this.cookie = cookie;
        this.localDate = LocalDateTime.parse(timestamp.trim(),
                DateTimeFormatter.ofPattern(PATTERN));
    }

    public String getCookie() {
        return cookie;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cookie).build();
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj instanceof CookieFormat) {
            final CookieFormat incoming = (CookieFormat) obj;
            isEqual = new EqualsBuilder().append(this.cookie,
                    incoming.cookie).build();
        }
        return isEqual;
    }

    @Override
    public int compareTo(CookieFormat obj) {
        return ChronoLocalDateTime.timeLineOrder().compare(this.getLocalDate(), obj.getLocalDate());
    }
}

package com.quantcast.demo.domain;

/**
 * <code>LogBinder</code> is the domain class to bind the csv fields in the
 * prescribed log format.
 */
public class LogBinder {
    private String cookie;

    private String timestamp;

    @Override
    public String toString() {
        return "demo.LogBinder{" +
                "cookie='" + cookie + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public String getCookie() {
        return cookie;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

package com.quantcast.demo.exception;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * <code>CsvParseException</code> exception class for better clarity in the
 * logs.
 */
public class CsvParseException extends Exception {
    private static final Logger logger =
            LogManager.getLogger((CsvParseException.class));

    public CsvParseException(String errorMessage, Throwable err) {
        super("Error caused while attempting parse: " + errorMessage, err);
    }
}

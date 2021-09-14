package com.quantcast.demo.util;

import com.quantcast.demo.domain.CookieFormat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <code>CookieAnalyzerUtils</code> hold the utility methods for processing.
 */
public class CookieAnalyzerUtils {

    private static final String PATTERN = "yyyy-MM-dd";
    private static final Logger logger =
            LogManager.getLogger((CookieAnalyzerUtils.class));

    /**
     * Sort the entry by values
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return sorted list
     */
    public static <K, V extends Comparable<V>>
    List<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {

        List<Map.Entry<K, V>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        return entryList;
    }

    /**
     * Get the maximum value(s) in the given sorted occurrences.
     *
     * @param entries
     * @return if any result is found, which ideally would be at least once,
     * in which case this is never null.(on second thoughts, optional could
     * be revisited).
     */
    public static Optional<List<Map.Entry<CookieFormat, Integer>>> getMaxSetOfEntries(List<Map.Entry<CookieFormat, Integer>> entries) {

        try {
            Map.Entry<CookieFormat, Integer> first = entries.iterator().next();
            Integer value = first.getValue();
            return Optional.of(entries.stream()
                    .filter(n -> n.getValue().equals(value))
                    .collect(Collectors.toList()));
        } catch (NoSuchElementException exc) {
            System.out.print("No entries found for given date");
        }
        return Optional.empty();
    }

    /**
     * Validate if entries are of a given date
     *
     * @param dateString
     * @return is of a valid date.
     */
    public static boolean validateDate(String dateString) {
        boolean isValidDate = true;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(PATTERN);
            LocalDate ld = LocalDate.parse(dateString, dtf);
        } catch (Exception exc) {
            isValidDate = false;
            logger.error("Date found to be not parseable. Please check the " +
                    "format!");
        }
        return isValidDate;
    }
}

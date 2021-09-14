package com.quantcast.demo.parser;

import com.quantcast.demo.domain.CookieFormat;
import com.quantcast.demo.domain.LogBinder;
import com.quantcast.demo.exception.CsvParseException;
import com.quantcast.demo.util.CookieAnalyzerUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collector;

/**
 * <code>CsvReadingProcessor</code> is the first functional entry point class.
 */
public class CsvReadingProcessor {
    private static final Logger logger =
            LogManager.getLogger((CsvReadingProcessor.class));
    private final String command;
    private final String filePath;
    private final String date;

    public CsvReadingProcessor(String command, String filePath, String date) {
        this.command = command;
        this.filePath = filePath;
        this.date = date;
    }

    public void process() throws CsvParseException {

        switch (command.toUpperCase()) {
            case "TRACK":
                CsvReader csvReader = new CsvReader(filePath);
                List<LogBinder> cookiesList = csvReader.process();
                CookiesCollector cookiesCollector = new CookiesCollector();
                Map<CookieFormat, Integer> cookiesMap =
                        cookiesList.stream().filter(item -> checkForDateMatch(item, date)).collect(Collector.of(cookiesCollector.supplier(),
                                cookiesCollector.accumulator(),
                                cookiesCollector.combiner(),
                                cookiesCollector.finisher()));
                findTheMaxValueOccurrance(cookiesMap);
                break;

            default:
                System.out.println("Some error has occurred. Please check the" +
                        " input format!");
                break;

        }

    }

    private void findTheMaxValueOccurrance(Map<CookieFormat, Integer> cookiesMap) {
        try {
            List<Map.Entry<CookieFormat, Integer>> entries =
                    CookieAnalyzerUtils.entriesSortedByValues(cookiesMap);
            Optional<List<Map.Entry<CookieFormat, Integer>>> maxSetOfEntries
                    = CookieAnalyzerUtils.getMaxSetOfEntries(entries);
            maxSetOfEntries.ifPresentOrElse(items -> {
                printResult(items, date);
            }, () -> {
                printResult(null, null);
            });
        } catch (NoSuchElementException nse) {
            logger.debug("No matching date found in the file!");
        }
    }

    private void printResult(List<Map.Entry<CookieFormat, Integer>> items,
                             String date) {
        if (items != null) {
            System.out.print(System.getProperty("line.separator") + "List of " +
                    "active cookies for the given date: " + date + System.getProperty("line.separator"));
            items.stream().forEach(p -> printCookie(p));

        } else {
            System.out.print("No active cookies found for the given date: " + date);
        }
    }

    /**
     * Print the cookie to standard output
     *
     * @param p
     */
    private void printCookie(Map.Entry<CookieFormat, Integer> p) {
        System.out.print(" Cookie " + p.getKey().getCookie() + " Found" + p.getValue() + " Occurrence: ");
    }

    /**
     * Validate if user entered a matching date with the cookies in the file.
     *
     * @param logBinder
     * @param date
     * @return boolean
     */
    private boolean checkForDateMatch(LogBinder logBinder, String date) {
        boolean isOfDate =
                logBinder.getTimestamp().substring(0, 10).equals(date.trim().substring(0, 10));
        return isOfDate;
    }
}

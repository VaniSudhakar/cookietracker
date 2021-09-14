package com.quantcast.demo.parser;

import com.quantcast.demo.domain.LogBinder;
import com.quantcast.demo.exception.CsvParseException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <code>CsvReader</code> parses the input csv to read logs.
 */
public class CsvReader {

    private final String fileName;
    private static final Logger logger =
            LogManager.getLogger((CsvReader.class));
    private static final char separator = ',';

    public CsvReader(String fileName) {
        this.fileName = fileName;
    }

    public List<LogBinder> process() throws CsvParseException {
        List<LogBinder> results = new ArrayList<>();
        try {
            try (BufferedReader br =
                         new BufferedReader(new FileReader(fileName))) {
                String line;
                List<String[]> tempList = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    tempList.add(line.split(","));
                }
                results = listAsObjects(tempList);
            }
            System.out.println("Parsed csv: " + fileName);
        } catch (IllegalStateException | IOException exc) {
            throw new CsvParseException(this.fileName, exc);
        }
        return results;
    }

    private List<LogBinder> listAsObjects(List<String[]> strings) {
        List<LogBinder> results = null;
        if (strings.size() > 1) {
            strings.remove(0);
            results = strings.stream().map(item -> {
                LogBinder logBinder = new LogBinder();
                item[0] = item[0].trim().replaceAll("\"", "").replaceAll("'"
                        , "");
                item[1] = item[1].trim().replaceAll("\"", "").replaceAll("'"
                        , "");
                logBinder.setCookie(item[0]);
                logBinder.setTimestamp(item[1]);
                return logBinder;
            }).collect(Collectors.toList());
        }
        return results;
    }


}
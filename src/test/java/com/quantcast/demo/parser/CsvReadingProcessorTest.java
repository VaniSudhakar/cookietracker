package com.quantcast.demo.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

class CsvReadingProcessorTest {

    @Test
    public void testProcess() {
        CsvReadingProcessor processor = new CsvReadingProcessor("TRACK", "src" +
                "/test/resources/sample_cookies.csv", "2018-12-09");
        assertAll(() -> processor.process());
    }

}
import com.quantcast.demo.exception.CsvParseException;
import com.quantcast.demo.parser.CsvReadingProcessor;
import com.quantcast.demo.util.CookieAnalyzerUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * <code>CookieAnalyzerApp</code> is the execution starting point.
 */
public class CookieAnalyzerApp {

    private static final Logger logger =
            LogManager.getLogger((CookieAnalyzerApp.class));

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Four args required in the format: -f " +
                    "sampleFilePath -d 2018-12-09");
            throw new IllegalArgumentException("Four args required in the " +
                    "format: -f 'filePath' -d date as 'yyyy-mm-dd'");
        }
        if (!"-f".equalsIgnoreCase(args[0])) {
            throw new IllegalArgumentException("First argument should be -f!");
        }
        File file = new File(args[1]);
        if (!args[1].endsWith(".csv") || !file.exists()) {
            throw new IllegalArgumentException("Second argument should be a " +
                    "valid csv file!");
        }
        if (!"-d".equalsIgnoreCase(args[2])) {
            throw new IllegalArgumentException("Third argument should be -d!");
        }
        if (!CookieAnalyzerUtils.validateDate(args[3])) {
            throw new IllegalArgumentException("Fourth argument should be " +
                    "date in the format yyyy-mm-dd");
        }
        CsvReadingProcessor processor = new CsvReadingProcessor("TRACK",args[1],args[3]);
        try {
            processor.process();
        } catch (CsvParseException e) {
            logger.error("Parsing of the csv file failed!"+ e.getMessage());
        }

    }
}

package ar.edu.itba.ppc.client.utilsConsole;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientParserHelper {
    private static final Logger logger = LoggerFactory.getLogger(ClientParserHelper.class);

    public static Map<String,String> parseArgs(String[] args) {
        Map<String,String> map = new HashMap<>();
        for (String arg : args) {
            String[] split = arg.substring(2).split("=");
            if (split.length == 2) {
                map.put(split[0], split[1]);
            }
        }
        return map;
    }

    public static void checkNullArgs(String arg, String msg) {
        if (arg == null) {
            logger.error(msg);
            System.exit(1);
        }
    }

    public static List<String[]> getCSVData(String path) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("File %s not found", path));
        }
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',').build();

        try (CSVReader reader = new CSVReaderBuilder(fileReader)
                .withSkipLines(1).withCSVParser(parser).build()) {
            return reader.readAll();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error reading CSV file: %s", path));
        }
    }

    public static boolean createOutputFile(String outPath, String string) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outPath));
            writer.write(string);
            writer.close();
            return true;
        } catch (IOException e) {
            logger.error("Error creating output file");
        }
        return false;
    }

    public static void printCSVData(String outPath) {
        try (FileReader fileReader = new FileReader(outPath);
             CSVReader reader = new CSVReaderBuilder(fileReader)
                     .withCSVParser(new CSVParserBuilder().withSeparator(',').build())
                     .build()) {

            List<String[]> allRows = reader.readAll();

            if (allRows.isEmpty()) {
                logger.warn("The CSV file is empty: {}", outPath);
                return;
            }

            // Imprimir encabezados
            System.out.println(String.join(", ", allRows.get(0)));

            // Imprimir datos
            for (int i = 1; i < allRows.size(); i++) {
                System.out.println(String.join(", ", allRows.get(i)));
            }

            logger.info("CSV data printed successfully from file: {}", outPath);
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", outPath, e);
        } catch (IOException e) {
            logger.error("Error reading CSV file: {}", outPath, e);
        }
    }
}
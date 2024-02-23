package src.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Logger {

    private File currentLogFile;

    private Logger() {
        LocalDate localDate = LocalDate.now();
        File logsFolder = new File("./logs");
        currentLogFile = new File("./logs/" + localDate + ".log");
        try {
            logsFolder.mkdirs();
            currentLogFile.createNewFile();
        } catch (IOException e) {
            System.out.println(ERROR_TAG + " Error while creating log file.");
        }
    }

    private static final Logger instance = new Logger();
    private static final String DEBUG_TAG = "[DEBUG]";
    private static final String ERROR_TAG = "[ERROR]";
    private static final String INFO_TAG = "[INFO]";
    private static final String LOG_FAILED = ERROR_TAG + " Failed to save log file";

    public static Logger getLogger() {
        return instance;
    }

    public void DEBUG(String message) {
        if (currentLogFile == null) {
            throw new IllegalStateException(LOG_FAILED);
        }
        String logString = getLogString(message, DEBUG_TAG);
        System.out.println(logString);
    }

    public void INFO(String message) {
        if (currentLogFile == null) {
            throw new IllegalStateException(LOG_FAILED);
        }
        String logString = getLogString(message, INFO_TAG);
        writeLogFile(logString);
        System.out.println(logString);
    }

    public void ERROR(String message) {
        if (currentLogFile == null) {
            throw new IllegalStateException(LOG_FAILED);
        }
        String logString = getLogString(message, ERROR_TAG);
        writeLogFile(logString);
        System.out.println(logString);
    }

    private static String getLogString(String message, String TAG) {
        return message.lines()
                .map(str -> LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")) + " " + TAG + ": " + str)
                .collect(Collectors.joining("\n"));
    }

    private void writeLogFile(String logString) {
        try (FileWriter fileWriter = new FileWriter(currentLogFile, true)) {
            fileWriter.write(logString + "\n");
        } catch (IOException e) {
            System.out.println(ERROR_TAG + " Error while appending log file.");
        }
    }

}

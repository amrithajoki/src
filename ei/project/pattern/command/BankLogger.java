package ei.project.pattern.command;

import java.io.IOException;
import java.util.logging.*;

// Logger Utility
// Writes all logs to file, but console only shows WARNING or ERROR
public class BankLogger {
    private static final Logger log = Logger.getLogger(BankLogger.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("BankTransactions.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            log.addHandler(fileHandler);

            log.setUseParentHandlers(false);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.WARNING); // WARNING+ only on console
            log.addHandler(consoleHandler);

            log.setLevel(Level.ALL);

        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed to initialize BankLogger", e);

        }
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void warning(String message) {
        log.warning(message);
    }

    public static void error(String message) {
        log.severe(message);
    }
}

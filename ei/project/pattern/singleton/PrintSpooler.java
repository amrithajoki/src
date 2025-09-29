package ei.project.pattern.singleton;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.*;

public class PrintSpooler {
    private static PrintSpooler instance;
    private Queue<PrintJob> jobQueue;
    private static final Logger logger = Logger.getLogger(PrintSpooler.class.getName());

    private PrintSpooler() {
        jobQueue = new LinkedList<>();

        try {
            // Log to a file called printspooler.log
            FileHandler fh = new FileHandler("printspooler.log", true); // true = append mode
            fh.setFormatter(new SimpleFormatter());

            // Clear default console handlers
            Logger rootLogger = Logger.getLogger("");
            for (Handler handler : rootLogger.getHandlers()) {
                rootLogger.removeHandler(handler);
            }

            // Add file + console handler
            logger.addHandler(fh);
            ConsoleHandler ch = new ConsoleHandler();
            logger.addHandler(ch);

            logger.setLevel(Level.ALL);
            fh.setLevel(Level.ALL);
            ch.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println(" Could not set up logger: " + e.getMessage());
        }
    }

    public static synchronized PrintSpooler getInstance() {
        if (instance == null) {
            instance = new PrintSpooler();
        }
        return instance;
    }

    public void addJob(PrintJob job) {
        jobQueue.add(job);
        logger.info("Job Added: " + job.getDocumentName() + " | Pages: " + job.getPages() +
                " | Orientation: " + job.getOrientation() + " | Paper: " + job.getPaperSize());
    }

    public void processAllJobs() {
        while (!jobQueue.isEmpty()) {
            PrintJob job = jobQueue.poll();

            if (job.getPages() > 50) {
                logger.severe("Problem: Cannot print " + job.getDocumentName() +
                        " (" + job.getPages() + " pages). Job skipped!");
                continue;
            }

            logger.info("Now Printing: " + job.getDocumentName() +
                    " (" + job.getPages() + " pages, " + job.getOrientation() +
                    ", " + job.getPaperSize() + ")");
            try {
                Thread.sleep(2000); // simulate print time
            } catch (InterruptedException e) {
                logger.warning("Printing interrupted for job: " + job.getDocumentName());
            }
            logger.info("Completed Printing: " + job.getDocumentName());
        }
    }
}

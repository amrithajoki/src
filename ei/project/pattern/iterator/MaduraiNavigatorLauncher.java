package ei.project.pattern.iterator;
import java.util.Scanner;
import java.util.Random;
import java.util.logging.*;

public class MaduraiNavigatorLauncher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        MaduraiSights madurai = new MaduraiSights();

        // Add Madurai sights
        madurai.addSight(new Sight("Meenakshi Temple", "Historic temple with stunning architecture."));
        madurai.addSight(new Sight("Thirumalai Nayakkar Palace", "Grand 17th-century palace."));
        madurai.addSight(new Sight("Gandhi Museum", "Museum dedicated to Mahatma Gandhi."));
        madurai.addSight(new Sight("Vandiyur Mariamman Teppakulam", "Famous temple tank with a floating festival."));
        madurai.addSight(new Sight("Alagar Kovil", "Temple located on a scenic hill."));

        Logger logger = Logger.getLogger("MaduraiNavigatorLog");
        logger.setUseParentHandlers(false);

        try {
            // FileHandler logs everything
            FileHandler fileHandler = new FileHandler("all_logs.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);

            // ConsoleHandler logs WARNING and above
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.WARNING);
            logger.addHandler(consoleHandler);

        } catch (Exception e) {
            System.out.println("Logger setup failed: " + e.getMessage());
        }

        boolean isAppRunning = true;

        while (isAppRunning) {
            System.out.println("\n=== Madurai Sightseeing Navigator ===");
            System.out.println("1. Wander randomly");
            System.out.println("2. Use Virtual Guide App");
            System.out.println("3. Hire Local Human Guide");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            SightIterator iterator;

            switch (choice) {
                case 1:
                    iterator = new RandomWandererIterator(madurai.getSights());
                    logger.info("User selected Random Wanderer mode.");
                    break;
                case 2:
                    iterator = new VirtualGuideIterator(madurai.getSights());
                    logger.info("User selected Virtual Guide mode.");
                    break;
                case 3:
                    System.out.print("Enter your interest (Temple, Palace, Museum): ");
                    String interest = scanner.nextLine();
                    iterator = new LocalGuideIterator(madurai.getSights(), interest);
                    logger.info("User selected Local Guide mode with interest: " + interest);
                    break;
                case 4:
                    System.out.println("Exiting... Enjoy your trip to Madurai!");
                    logger.info("User exited the app.");
                    isAppRunning = false;
                    continue;
                default:
                    System.out.println("Invalid choice. Try again.");
                    logger.warning("Invalid main menu choice entered: " + choice);
                    continue;
            }

            boolean isTourRunning = true;
            while (isTourRunning) {
                Sight currentSight = iterator.next();
                if (currentSight == null) {
                    System.out.println("No more sights to show.");
                    logger.warning("No more sights for the current tour.");
                    break;
                }

                System.out.println("\nCurrent Sight:");
                System.out.println(currentSight);
                logger.info("Visited sight: " + currentSight.getName());

                // Random festival for Random Wanderer
                if (choice == 1 && random.nextInt(3) == 0) {
                    System.out.println("Surprise! You stumbled upon a local festival nearby!");
                    logger.warning("Random festival event at: " + currentSight.getName());
                }

                System.out.print("Enter command (next, prev, exit): ");
                String cmd = scanner.nextLine();

                switch (cmd.toLowerCase()) {
                    case "next":
                        if (!iterator.hasNext()) {
                            System.out.println("Reached the end of sights.");
                            logger.warning("User reached end of sights.");
                        }
                        break;
                    case "prev":
                        if (iterator.hasPrevious()) {
                            Sight prev = iterator.previous();
                            System.out.println(prev);
                            logger.info("User viewed previous sight: " + prev.getName());
                        } else {
                            System.out.println("No previous sight.");
                            logger.warning("User tried to go to previous but none exists.");
                        }
                        break;
                    case "exit":
                        isTourRunning = false;
                        logger.info("User exited the current tour mode.");
                        break;
                    default:
                        System.out.println("Invalid command.");
                        logger.warning("Invalid tour command entered: " + cmd);
                        break;
                }
            }
        }

        scanner.close();
    }
}


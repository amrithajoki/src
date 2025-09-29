package ei.project.pattern.decorator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;

public class PizzaOrderApp {

    public static Logger logger = Logger.getLogger("PizzaLogger");

    static {
        try {
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler h : handlers) rootLogger.removeHandler(h);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setFilter(record -> record.getLevel().intValue() >= Level.WARNING.intValue());

            FileHandler fileHandler = new FileHandler("pizza_order.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setFilter(record -> record.getLevel().intValue() < Level.WARNING.intValue());

            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);

        } catch (Exception e) {
            System.err.println("Logger initialization failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pizza pizza = new PlainPizza(); // base pizza

        logger.info("=== Pizza Ordering System Started ===");
        System.out.println("=== Welcome to Pizza Ordering System ===");
        System.out.println("Base Pizza: Plain Pizza (200 rupees)");

        boolean ordering = true;
        while (ordering) {
            try {
                System.out.println("\nWhat would you like to add on your Plain Pizza?");
                System.out.println("1. Cheese (+50 rupees)");
                System.out.println("2. Olives (+30 rupees)");
                System.out.println("3. Paneer (+70 rupees)");
                System.out.println("4. Chicken (+100 rupees)");
                System.out.println("5. Done");
                System.out.println("Do you want to add another topping? (choose a number or 5 to finish)");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        pizza = new CheeseDecorator(pizza);
                        logger.info("Added Cheese");
                    }
                    case 2 -> {
                        pizza = new OliveDecorator(pizza);
                        logger.info("Added Olives");
                    }
                    case 3 -> {
                        pizza = new PaneerDecorator(pizza);
                        logger.info("Added Paneer");
                    }
                    case 4 -> {
                        pizza = new ChickenDecorator(pizza);
                        logger.info("Added Chicken");
                    }
                    case 5 -> {
                        ordering = false;
                        logger.info("Finished Ordering");
                    }
                    default -> {
                        logger.warning("Invalid choice entered: " + choice);
                        System.out.println("Invalid choice. Try again!");
                    }
                }
            } catch (InputMismatchException e) {
                logger.severe("Invalid input type entered: " + e.getMessage());
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // clear buffer
            } catch (Exception e) {
                logger.severe("Unexpected error: " + e.getMessage());
                System.out.println("Something went wrong. Try again.");
            }
        }

        System.out.println("\nYour Order:");
        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Total Cost: " + pizza.getCost() + " rupees");

        logger.info("Order Completed: " + pizza.getDescription() + " | Total Cost: " + pizza.getCost() + " rupees");
    }
}


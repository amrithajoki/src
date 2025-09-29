package ei.project.pattern.facade;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

/**
 * Facade class (HotelKeeper)
 * - Provides a simple interface to access different cuisines.
 * - Handles logging, error handling, and allergy warnings.
 */
public class HotelKeeperFacade {

    private static final Logger logger = Logger.getLogger(HotelKeeperFacade.class.getName());

    // Initialize logger
    static {
        try {
            // File logging (all levels go to file)
            FileHandler fileHandler = new FileHandler("hotel.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);

            // Console logging (only WARNING and SEVERE visible to client)
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.WARNING);

            logger.setUseParentHandlers(false); // disable default console
            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Logger initialization failed: " + e.getMessage());
        }
    }

    public String getMenu(String cuisineType, String allergy) {
        CuisineTypes.Cuisine cuisine = null;

        try {
            switch (cuisineType.toLowerCase()) {
                case "italian":
                    cuisine = new CuisineTypes.ItalianCuisine();
                    break;
                case "chinese":
                    cuisine = new CuisineTypes.ChineseCuisine();
                    break;
                case "indian":
                    cuisine = new CuisineTypes.IndianCuisine();
                    break;
                default:
                    logger.warning("Invalid cuisine requested: " + cuisineType);
                    return "Sorry, we do not serve " + cuisineType + " cuisine.";
            }

            // Cast to access items for allergy check
            List<String> items = null;
            if (cuisine instanceof CuisineTypes.ItalianCuisine) {
                items = ((CuisineTypes.ItalianCuisine) cuisine).getItems();
            } else if (cuisine instanceof CuisineTypes.ChineseCuisine) {
                items = ((CuisineTypes.ChineseCuisine) cuisine).getItems();
            } else if (cuisine instanceof CuisineTypes.IndianCuisine) {
                items = ((CuisineTypes.IndianCuisine) cuisine).getItems();
            }

            // Allergy check
            if (allergy != null && !allergy.isEmpty()) {
                for (String item : items) {
                    if (item.toLowerCase().contains(allergy.toLowerCase())) {
                        logger.severe("ALERT: Allergy item found! Cuisine: " + cuisineType + ", Item: " + item);
                        return "⚠ Warning: The " + cuisineType + " menu contains '" + item +
                               "' which you are allergic to!";
                    }
                }
            }

            logger.info("Menu delivered for cuisine: " + cuisineType);
            return cuisine.getMenu();

        } catch (Exception e) {
            logger.severe("Error while fetching menu: " + e.getMessage());
            return "An error occurred while fetching the menu. Please try again.";
        }
    }
}

package ei.project.pattern.facade;

import java.util.Arrays;
import java.util.List;

/**
 * Cuisine interface + Different cuisine implementations
 * (All subsystems placed in one file for simplicity)
 */
public class CuisineTypes {

    // ================= Common Interface =================
    public interface Cuisine {
        String getMenu();
    }

    // ================= Italian Cuisine =================
    public static class ItalianCuisine implements Cuisine {
        private final List<String> menuItems = Arrays.asList("Pizza", "Pasta", "Lasagna", "Cheese Garlic Bread");

        @Override
        public String getMenu() {
            return "Italian Menu: " + String.join(", ", menuItems);
        }

        public List<String> getItems() {
            return menuItems;
        }
    }

    // ================= Chinese Cuisine =================
    public static class ChineseCuisine implements Cuisine {
        private final List<String> menuItems = Arrays.asList("Noodles", "Manchurian", "Spring Rolls", "Soy Sauce Chicken","Fried Rice");

        @Override
        public String getMenu() {
            return "Chinese Menu: " + String.join(", ", menuItems);
        }

        public List<String> getItems() {
            return menuItems;
        }
    }

    // ================= Indian Cuisine =================
    public static class IndianCuisine implements Cuisine {
        private final List<String> menuItems = Arrays.asList("Biryani", "Paneer Butter Masala", "Naan", "Dal Tadka","Pongal","Indian meals","rice");

        @Override
        public String getMenu() {
            return "Indian Menu: " + String.join(", ", menuItems);
        }

        public List<String> getItems() {
            return menuItems;
        }
    }
}

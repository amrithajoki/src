package ei.project.pattern.facade;

import java.util.Scanner;

/**
 * HotelAppLauncher - Client
 * - Entry point of the Hotel system.
 * - User interacts only with HotelKeeperFacade (not subsystems directly).
 */
public class HotelAppLauncher {
    public static void main(String[] args) {
        HotelKeeperFacade hotelKeeper = new HotelKeeperFacade();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the Hotel ===");
        System.out.print("Enter cuisine type (Italian/Chinese/Indian): ");
        String cuisineType = scanner.nextLine();

        System.out.print("Do you have any food allergies? (enter item name or leave blank): ");
        String allergy = scanner.nextLine();

        String menu = hotelKeeper.getMenu(cuisineType, allergy);
        System.out.println("\n" + menu);

        scanner.close();
    }
}

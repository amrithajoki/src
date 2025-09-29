package ei.project.pattern.singleton;

import java.util.Scanner;

public class PrintSpoolerLauncher {

    public static void main(String[] args) {
        PrintSpooler spooler = PrintSpooler.getInstance();
        Scanner scanner = new Scanner(System.in);

        boolean addMoreJobs = true;

        System.out.println("=== Welcome to the Print Spooler ===");

        while (addMoreJobs) {
            // Collect job details
            System.out.print("\nEnter document name: ");
            String docName = scanner.nextLine();

            System.out.print("Enter number of pages: ");
            int pages;
            try {
                pages = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid page number! Job not added.");
                continue;
            }

            System.out.print("Enter orientation (Landscape/Portrait): ");
            String orientation = scanner.nextLine();

            System.out.print("Enter paper size (A4/A3/Letter): ");
            String paperSize = scanner.nextLine();

            // Add job to spooler
            PrintJob job = new PrintJob(docName, pages, orientation, paperSize);
            spooler.addJob(job);

            // Ask if user wants to add more jobs
            System.out.print("\nDo you want to add another job? (yes/no): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                addMoreJobs = false;
            }
        }

        // Process all queued jobs
        System.out.println("\n=== Processing Jobs in Queue ===");
        spooler.processAllJobs();

        scanner.close();
    }
}





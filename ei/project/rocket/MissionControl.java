package ei.project.rocket;

import ei.project.util.PrintLog;

import java.util.Scanner;
import java.util.logging.*;

public class MissionControl {

    private static MissionControl instance;
    
    // Shared logger for console + file logging
    public static final Logger logger = Logger.getLogger(MissionControl.class.getName());

    private Rocket rocket = new Rocket(); // The rocket being simulated

    // ---------------- Singleton constructor ----------------
    private MissionControl() {
        try {
            LogManager.getLogManager().reset(); // reset default log configuration
            logger.setLevel(Level.ALL);

            // 1️⃣ Console logging
            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.ALL);
            logger.addHandler(ch);

            // 2️⃣ File logging (rocket_logs.txt)
            FileHandler fh = new FileHandler("rocket_logs.txt", true); // append mode
            fh.setLevel(Level.ALL);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- Singleton getInstance ----------------
    public static MissionControl getInstance() {
        if (instance == null) instance = new MissionControl();
        return instance;
    }

    public Rocket getRocket() { return rocket; }

    // ---------------- Normal launch simulation ----------------
    public void runSimulation(boolean fastMode) throws Exception {
        int delay = fastMode ? 0 : 1500; // delay between stages
        Stage stage1 = new RocketStages.Stage1();
        Stage stage2 = new RocketStages.Stage2();

        stage1.execute(rocket);        // ei.project.rocket.Stage 1
        if (!fastMode) Thread.sleep(delay);

        stage2.execute(rocket);        // ei.project.rocket.Stage 2
    }

    // ---------------- Fast-forward simulation for X seconds ----------------
    public void runSimulationFastForward(int seconds) throws Exception {
        Stage stage1 = new RocketStages.Stage1();
        Stage stage2 = new RocketStages.Stage2();

        for (int i = 1; i <= seconds; i++) {
            System.out.println("T+" + i + "s (Fast Forward)");
            logger.info("T+" + i + "s (Fast Forward)");

            // Simplified: execute stages at first and second second
            if (i == 1) stage1.execute(rocket);
            else if (i == 2) stage2.execute(rocket);

            Thread.sleep(300); // small delay for readability
        }
    }

    // ---------------- Main command loop ----------------
    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Welcome to ei.project.rocket.Rocket Launch Simulator ===");
            logger.info("Simulator started.");

            while (true) {
                System.out.print("Enter command (precheck / launch / forward X / abort / exit): ");
                String input = scanner.nextLine().trim();
                Command command = null;

                try {
                    // ---------------- Fast Forward parsing ----------------
                    if (input.startsWith("forward")) {
                        String[] parts = input.split(" ");
                        int seconds = 2; // default if not specified
                        if (parts.length == 2) {
                            try {
                                seconds = Integer.parseInt(parts[1]);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number. Using default 2 seconds.");
                                logger.warning("Invalid forward number: " + parts[1]);
                            }
                        }
                        // ei.project.rocket.Command pattern: ei.project.rocket.FastForwardWithSecondsCommand
                        command = new FastForwardWithSecondsCommand(this, seconds);

                    } else {
                        // ---------------- Other commands ----------------
                        switch (input) {
                            case "precheck" -> command = new StartChecksCommand(this);
                            case "launch" -> command = new LaunchCommand(this);
                            case "abort" -> command = new AbortCommand(this);
                            case "exit" -> {
                                System.out.println("Exiting simulator.");
                                logger.info("Simulator exited by user.");
                                return; // exit loop
                            }
                            default -> {
                                System.out.println("Invalid command.");
                                logger.warning("Invalid command entered: " + input);
                            }
                        }
                    }

                    // Execute the command if valid
                    if (command != null) command.execute();

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.severe("ei.project.rocket.Command execution error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    // ---------------- Mission summary ----------------
    public void printMissionSummary() {
        System.out.println("\n=== Mission Summary ===");
        System.out.println("Status        : " + (rocket.isMissionSuccess() ? "SUCCESS" : "FAILURE"));
        System.out.println("Reason        : " + (rocket.isMissionSuccess() ? "Orbit achieved" : rocket.getFailureReason()));
        System.out.println("Max Altitude  : " + rocket.getAltitude() + " km");
        System.out.println("Max Speed     : " + rocket.getSpeed() + " km/h");
        System.out.println("Remaining Fuel: " + rocket.getFuel() + "%");
        System.out.println("=======================");

        logger.info("Mission Summary - Status: " + (rocket.isMissionSuccess() ? "SUCCESS" : "FAILURE") +
                    ", Reason: " + (rocket.isMissionSuccess() ? "Orbit achieved" : rocket.getFailureReason()) +
                    ", Altitude: " + rocket.getAltitude() +
                    ", Speed: " + rocket.getSpeed() +
                    ", Fuel: " + rocket.getFuel());
    }

    // ---------------- Inner ei.project.rocket.Command for Fast-Forward ----------------
    private static class FastForwardWithSecondsCommand implements Command {
        private MissionControl control;
        private int seconds;

        public FastForwardWithSecondsCommand(MissionControl control, int seconds) {
            this.control = control;
            this.seconds = seconds;
        }

        @Override
        public void execute() throws Exception {
            System.out.println("Fast-forwarding simulation by " + seconds + " seconds...");
            logger.info("Executing fast-forward for " + seconds + " seconds");
            control.runSimulationFastForward(seconds);
            control.printMissionSummary();
        }
    }
}

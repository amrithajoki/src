package ei.project.rocket;

import java.util.logging.Logger;

public class RocketStages {
    private static final Logger logger = MissionControl.logger; // Use shared logger

    public static class Stage1 implements Stage {
        public void execute(Rocket rocket) throws InterruptedException {
            rocket.consumeFuel(30);
            rocket.increaseAltitude(100);
            rocket.increaseSpeed(1000);
            System.out.printf("T+1s | Fuel: %d%% | Altitude: %d km | Speed: %d km/h%n",
                    rocket.getFuel(), rocket.getAltitude(), rocket.getSpeed());
            logger.info("ei.project.rocket.Stage 1 complete. Fuel: " + rocket.getFuel() +
                        ", Altitude: " + rocket.getAltitude() +
                        ", Speed: " + rocket.getSpeed());
            Thread.sleep(1500);
            System.out.println("ei.project.rocket.Stage 1 complete. Entering ei.project.rocket.Stage 2.");
        }
    }

    public static class Stage2 implements Stage {
        public void execute(Rocket rocket) throws InterruptedException {
            rocket.consumeFuel(50);
            rocket.increaseAltitude(200);
            rocket.increaseSpeed(2000);
            System.out.printf("T+2s | Fuel: %d%% | Altitude: %d km | Speed: %d km/h%n",
                    rocket.getFuel(), rocket.getAltitude(), rocket.getSpeed());
            logger.info("ei.project.rocket.Stage 2 in progress. Fuel: " + rocket.getFuel() +
                        ", Altitude: " + rocket.getAltitude() +
                        ", Speed: " + rocket.getSpeed());
            Thread.sleep(1500);

            if (rocket.getFuel() <= 10) {
                rocket.setFailureReason("Insufficient fuel");
                new MissionFailed().execute(rocket);
            } else if (FailureSimulator.checkFailure()) {
                rocket.setFailureReason("System failure");
                new MissionFailed().execute(rocket);
            } else {
                new OrbitAchieved().execute(rocket);
            }
        }
    }

    public static class OrbitAchieved implements Stage {
        public void execute(Rocket rocket) {
            rocket.setMissionSuccess(true);
            System.out.println("Orbit achieved! Mission Successful.");
            logger.info("Mission Successful.");
        }
    }

    public static class MissionFailed implements Stage {
        public void execute(Rocket rocket) {
            rocket.setMissionSuccess(false);
            if (rocket.getFailureReason().isEmpty()) {
                rocket.setFailureReason("Aborted by user");
            }
            System.out.println("Mission Failed: " + rocket.getFailureReason());
            logger.warning("Mission Failed: " + rocket.getFailureReason());
        }
    }
}

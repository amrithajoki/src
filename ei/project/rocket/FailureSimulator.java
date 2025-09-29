package ei.project.rocket;

import java.util.Random;

public class FailureSimulator {
    private static final Random random = new Random();

    public static boolean checkFailure() {
        // 20% chance of random system failure
        return random.nextInt(100) < 20;
    }
}

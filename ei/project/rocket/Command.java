package ei.project.rocket;

import java.util.logging.Logger;

public interface Command {
    void execute() throws Exception;
}

class StartChecksCommand implements Command {
    private MissionControl control;
    private static final Logger logger = Logger.getLogger(StartChecksCommand.class.getName());

    public StartChecksCommand(MissionControl control) { this.control = control; }

    @Override
    public void execute() throws InterruptedException {
        System.out.println("Performing pre-launch checks...");
        for (int i = 5; i > 0; i--) {
            System.out.println("Countdown: " + i);
            Thread.sleep(1000); // 1 second delay
        }
        System.out.println("All systems are 'Go' for launch!");
        logger.info("Pre-launch checks completed.");
    }
}

class LaunchCommand implements Command {
    private MissionControl control;

    public LaunchCommand(MissionControl control) { this.control = control; }

    @Override
    public void execute() throws Exception {
        control.runSimulation(false);
        control.printMissionSummary();
    }
}

class FastForwardCommand implements Command {
    private MissionControl control;

    public FastForwardCommand(MissionControl control) { this.control = control; }

    @Override
    public void execute() throws Exception {
        System.out.println("Fast-forwarding simulation...");
        control.runSimulation(true);
        control.printMissionSummary();
    }
}

class AbortCommand implements Command {
    private MissionControl control;

    public AbortCommand(MissionControl control) { this.control = control; }

    @Override
    public void execute() {
        control.getRocket().setFailureReason("Aborted by user");
        new RocketStages.MissionFailed().execute(control.getRocket());
        control.printMissionSummary();
    }
}

package ei.project.rocket;

// Concrete ei.project.rocket.Command to handle fast_forward X
public class FastForwardWithSecondsCommand implements Command {
    private MissionControl control;
    private int seconds; // number of seconds to fast-forward

    public FastForwardWithSecondsCommand(MissionControl control, int seconds) {
        this.control = control;
        this.seconds = seconds;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Fast-forwarding simulation by " + seconds + " seconds...");
        control.runSimulationFastForward(seconds); // call the method in ei.project.rocket.MissionControl
        control.printMissionSummary();             // show mission summary after fast-forward
    }
}


package ei.project.rocket;

public class RocketSimulatorApp {
 public static void main(String[] args) {
        MissionControl control = MissionControl.getInstance();
        control.start();
  }
}


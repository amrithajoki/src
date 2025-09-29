package ei.project.rocket;

public class Rocket {
    private int fuel = 100;
    private int altitude = 0;
    private int speed = 0;
    private boolean missionSuccess = false;
    private String failureReason = "";

    public int getFuel() { return fuel; }
    public int getAltitude() { return altitude; }
    public int getSpeed() { return speed; }
    public boolean isMissionSuccess() { return missionSuccess; }
    public String getFailureReason() { return failureReason; }

    public void consumeFuel(int amount) { fuel = Math.max(0, fuel - amount); }
    public void increaseAltitude(int km) { altitude += km; }
    public void increaseSpeed(int kmh) { speed += kmh; }
    public void setMissionSuccess(boolean success) { missionSuccess = success; }
    public void setFailureReason(String reason) { failureReason = reason; }
}

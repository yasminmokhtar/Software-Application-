package Entity;

public class Goal {
    private String goalId;
    private String userId;
    private String type;
    private double target;
    private double progress;

    public Goal(String goalId, String userId, String type, double target, double progress) {
        this.goalId = goalId;
        this.userId = userId;
        this.type = type;
        this.target = target;
        this.progress = progress;
    }

    public String getGoalId() {
        return goalId;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public double getTarget() {
        return target;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void setTarget(double target) {
        this.target = target;
    }
}

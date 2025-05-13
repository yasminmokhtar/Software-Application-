package Entity;

public class Goal {
    private String userId;
    private String goalId;
    private String type;
    private double target;
    private String deadline;
    private double progress;

    public Goal(String userId, String goalId, String type, double target, String deadline, double progress) {
        this.userId = userId;
        this.goalId = goalId;
        this.type = type;
        this.target = target;
        this.deadline = deadline;
        this.progress = progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setGoalId(String goalId) {
        this.goalId = goalId;
    }

    public String getUserId() {
        return userId;
    }

    public String getGoalId() {
        return goalId;
    }

    public String getType() {
        return type;
    }

    public double getTarget() {
        return target;
    }

    public String getDeadline() {
        return deadline;
    }

    public double getProgress() {
        return progress;
    }
}

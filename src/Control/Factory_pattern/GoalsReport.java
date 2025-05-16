package Control.Factory_pattern;

import Control.GoalsController;
import Entity.Goal;
import java.util.List;

public class GoalsReport implements Report {
    private final GoalsController goalsController;

    public GoalsReport(GoalsController goalsController) {
        this.goalsController = goalsController;
    }

    @Override
    public void generate(String userId) {
        List<Goal> goals = goalsController.viewGoals(userId);
        System.out.println("=== Financial Goals (" + goals.size() + ") ===");
        for (Goal g : goals) {
            System.out.println(
                    " - [" + g.getGoalId() + "] " + g.getType() +
                            ": progress " + g.getProgress() + "/" + g.getTarget());
        }
        System.out.println();
    }
}
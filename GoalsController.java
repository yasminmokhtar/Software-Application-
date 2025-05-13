package Control;

import Entity.Goal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoalsController {

    // Store goals per userId
    private Map<String, List<Goal>> userGoals;

    public GoalsController() {
        userGoals = new HashMap<>();
    }

    // Add a goal for a user
    public boolean addGoal(Goal g) {
        String userId = g.getUserId();
        userGoals.putIfAbsent(userId, new ArrayList<>());
        userGoals.get(userId).add(g);
        System.out.println("Goal added for user: " + userId);
        return true;
    }

    // View all goals for a user
    public List<Goal> viewGoals(String userId) {
        List<Goal> goals = userGoals.get(userId);

        if (goals == null || goals.isEmpty()) {
            System.out.println("No goals found for user: " + userId);
            return Collections.emptyList();
        }

        System.out.println("Goals for user " + userId + ":");
        for (Goal g : goals) {
            System.out.printf(
                    "- %s (%s): %.2f/%.2f%n",
                    g.getGoalId(), g.getType(), g.getProgress(), g.getTarget());
        }
        return goals;
    }
}

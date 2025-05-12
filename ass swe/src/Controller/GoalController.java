package Controller;

import Entity.Goal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoalController {
    private Map<String, List<Goal>> userGoals = new HashMap<>();

    // إضافة هدف جديد
    public boolean addGoal(String userId, Goal goal) {
        if (!userGoals.containsKey(userId)) {
            userGoals.put(userId, new ArrayList<>());
        }
        userGoals.get(userId).add(goal);
        System.out.println("Goal added for user: " + userId);
        return true;
    }

    // عرض أهداف المستخدم
    public void viewGoals(String userId) {
        List<Goal> goals = userGoals.get(userId);
        if (goals == null || goals.isEmpty()) {
            System.out.println("No goals found for user: " + userId);
        } else {
            System.out.println("Goals for user " + userId + ":");
            for (Goal goal : goals) {
                System.out.println("- Goal ID: " + goal.getGoalId() + ", Target: " + goal.getTarget());
            }
        }
    }
}
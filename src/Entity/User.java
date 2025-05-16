package Entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String userId;
    private String password;
    private List<Goal> goals = new ArrayList<>();

    public User(String name, String email, String userId) {
        this.name = name;
        this.email = email;
        this.userId = userId;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public String getPassword() {
        return password;
    }

}
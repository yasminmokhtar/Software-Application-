package Controller;

import Entity.User;
import java.util.List;
import java.util.ArrayList;

public class AuthController {

    private List<User> users = new ArrayList<>();

    public void signUp(User user) {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                System.out.println("User already exists");
                return;
            }
        }
        users.add(user);
        System.out.println("User signed up successfully");
    }

    public void login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                System.out.println("User logged in successfully");
                return;
            }
        }
        System.out.println("Invalid email or password");
    }
}

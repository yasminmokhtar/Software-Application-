package Control;

import Entity.User;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationController {
    private List<User> users = new ArrayList<>();

    public boolean signup(User user) {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                return false;
            }
        }

        users.add(user);
        return true;
    }

    public User login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                System.out.println("User logged in successfully");
                return u;
            }
        }
        System.out.println("Invalid email or password");
        return null;
    }
}


package com.example.simplerestapi.service;

import com.example.simplerestapi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    // Create some dummy data for testing
    public UserService() {
        users.add(new User(1L, "John Doe", "john.doe@example.com"));
        users.add(new User(2L, "Jane Smith", "jane.smith@example.com"));
    }

    // Get all users
    public List<User> getAllUsers() {
        return users;
    }

    // Get a single user by ID
    public Optional<User> getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    // Add a new user
    public User addUser(User user) {
        user.setId((long) (users.size() + 1));  // Simple auto-increment ID
        users.add(user);
        return user;
    }

    // Update an existing user
    public Optional<User> updateUser(Long id, User userDetails) {
        Optional<User> userOptional = getUserById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return Optional.of(user);
        }
        return Optional.empty();
    }

    // Delete a user
    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}

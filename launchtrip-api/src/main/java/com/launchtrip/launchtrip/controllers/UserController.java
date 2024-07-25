package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.User;
import com.launchtrip.launchtrip.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @PostMapping("/new")
    public User createUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName) {
        User newUser = new User(username, password, firstName, lastName);
        return userRepository.save(newUser);
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/edit")
    public void updateUser(@RequestParam Long userId, @RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName) {
        User userToUpdate = userRepository.getReferenceById(userId);
        userToUpdate.setUsername(username);
        userToUpdate.setPassword(password);
        userToUpdate.setFirstName(firstName);
        userToUpdate.setLastName(lastName);
        userRepository.save(userToUpdate);
    }

}

package com.launchtrip.launchtrip.controllers;

import com.launchtrip.launchtrip.models.data.UserRepository;
import com.launchtrip.launchtrip.models.User;
import com.launchtrip.launchtrip.models.RegisterDTO;
import com.launchtrip.launchtrip.models.LoginDTO;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/Auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(Long.valueOf(userId));

        return user.orElse(null);

    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @PostMapping("/register")
    public ResponseEntity<?> processRegistrationForm(@RequestBody @Valid RegisterDTO registerFormDTO,
                                                     Errors errors, HttpServletRequest request) {

        Map<String, String> response = new HashMap<>();

        if (errors.hasErrors()) {
            response.put("message", "Registration form contains errors");
            return ResponseEntity.badRequest().body(response);
        }

        User existingUser = userRepository.findByUsername(LoginDTO.getUsername());

        if (existingUser != null) {
            response.put("message", "A user with that username already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        String password = LoginDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            response.put("message", "Passwords do not match");
            return ResponseEntity.badRequest().body(response);
        }

        User newUser = new User(RegisterDTO.getUsername(), RegisterDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        response.put("message", "Registration successful");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> processLoginForm(@RequestBody @Valid LoginDTO loginFormDTO,
                                              Errors errors, HttpServletRequest request) {

        Map<String, String> response = new HashMap<>();

        if (errors.hasErrors()) {
            response.put("message", "Login form contains errors");
            return ResponseEntity.badRequest().body(response);
        }

        User theUser = userRepository.findByUsername(LoginDTO.getUsername());

        if (theUser == null) {
            response.put("message", "The given username does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        String password = LoginDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            response.put("message", "Invalid password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        setUserInSession(request.getSession(), theUser);

        response.put("message", "Login successful");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return ResponseEntity.ok(response);
    }
}
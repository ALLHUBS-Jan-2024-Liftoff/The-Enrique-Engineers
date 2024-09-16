
package com.launchtrip.launchtrip.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import com.launchtrip.launchtrip.models.User;
import com.launchtrip.launchtrip.models.data.UserRepository;
import com.launchtrip.launchtrip.models.LoginDTO;
import com.launchtrip.launchtrip.models.RegisterDTO;
import com.launchtrip.launchtrip.models.ResetPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";
    // Endpoint to get the user ID based on the username
    @GetMapping("/user")
    public ResponseEntity<Integer> getUserID(@RequestParam String username)
    {
        User user = userRepository.findByUsername(username);

        if(user != null)
        {
            return ResponseEntity.ok(Math.toIntExact(user.getId()));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    // Retrieves the user from the HTTP session //
    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }
    // Stores the user's ID in the HTTP session
    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }
    // uses a 409 conflict error if the username already exist //
//    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> processRegistrationForm(@RequestBody @Valid RegisterDTO registerFormDTO,
                                                     Errors errors, HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();

        if (errors.hasErrors()) {
            response.put("message", "Registration form contains errors");
            return ResponseEntity.badRequest().body(response);
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            response.put("message", "A user with that username already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            response.put("message", "Passwords do not match");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
            userRepository.save(newUser);
            setUserInSession(request.getSession(), newUser);

            response.put("message", "Registration successful");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace to console
            response.put("message", "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }}
    // Handles user login

    // 400 bad request if errors //
    // 404 not found if username does not exist//
    // 401 unauthorized if the password is incorrect//
    // 200 is a successful login //
//    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> processLoginForm(@RequestBody @Valid LoginDTO loginFormDTO,
                                              Errors errors, HttpServletRequest request) {

        Map<String, String> response = new HashMap<>();

        if (errors.hasErrors()) {
            response.put("message", "Login form contains errors");
            return ResponseEntity.badRequest().body(response);
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            response.put("message", "The given username does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            response.put("message", "Invalid password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        setUserInSession(request.getSession(), theUser);

        response.put("message", "Login successful");
        return ResponseEntity.ok(response);
    }
    // invalidates user session, 200 is logout success //
//    @CrossOrigin
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return ResponseEntity.ok(response);
    }

    //    @CrossOrigin
    // New endpoint for resetting the password without using a token
    // 400 bad request errors or passwords don't match
    // 404 not found, if user name does not exist
    // 200 password is successfully reset
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordDTO resetPasswordDTO, Errors errors) {
        Map<String, String> response = new HashMap<>();

        if (errors.hasErrors()) {
            response.put("message", "Password reset form contains errors");
            return ResponseEntity.badRequest().body(response);
        }

        if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
            response.put("message", "Passwords do not match");
            return ResponseEntity.badRequest().body(response);
        }

        User user = userRepository.findByUsername(resetPasswordDTO.getUsername());

        if (user == null) {
            response.put("message", "User with this username does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Update the user's password
        user.setPassword(resetPasswordDTO.getNewPassword());  // Assuming `setPassword` hashes the password
        userRepository.save(user);

        response.put("message", "Password reset successful");
        return ResponseEntity.ok(response);
    }

}
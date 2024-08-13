package com.launchtrip.launchtrip.models;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid username. Must be between 3 and 20 characters.")
    private static String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 30, message = "Invalid password. Must be between 5 and 30 characters.")
    private static String password;

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        LoginDTO.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        LoginDTO.password = password;
    }

}
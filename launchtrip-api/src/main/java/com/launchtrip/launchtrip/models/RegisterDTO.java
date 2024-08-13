package com.launchtrip.launchtrip.models;
public class RegisterDTO extends LoginDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public Object getFirstName() {
        return null;
    }
}
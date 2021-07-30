package com.example.backback.dto.request;

import java.util.Set;

public class SignUpForm {
    private String username;
    private String email;
    private String password;
    private String phone;
    private String dateofbirth;
    private String city;

    private Set<String> roles;

    public SignUpForm() {
    }

    public SignUpForm(String username, String email, String password, String phone, String dateofbirth, String city, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.dateofbirth = dateofbirth;
        this.city = city;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

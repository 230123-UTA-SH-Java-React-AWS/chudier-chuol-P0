package com.revature.model;

public class Employee {
    
    private int id;
    private String email;
    private String password;
    private String role;

    public int getEmployeeid() {
        return id;
    }

    public void setEmployeeid(int id) {
        this.id = id;
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
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
    }
}
package com.revature.model;

import java.util.List;

public class Employee {
    
    private String email;
    private String password;

    private List <reimbursementRequest> tickets;

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

    public List <reimbursementRequest> getTickets() {
        return tickets;
    }

    public void setTickets(List <reimbursementRequest> tickets) {
        this.tickets = tickets;
    }

    void login() {
        //
    }

}
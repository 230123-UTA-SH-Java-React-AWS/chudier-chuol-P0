package com.revature.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    
    private int employeeid;
    private String email;
    private String password;

    private List <Ticket> tickets;
    
    public Employee() {
        this.tickets = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Employee [employeeid=" + employeeid + ", email=" + email + ", password=" + password + ", tickets="
                + tickets + "]";
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
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

    public List <Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List <Ticket> tickets) {
        this.tickets = tickets;
    }

}
package com.revature.model;

public class Manager extends Employee implements ticketingSystem {
    
    boolean isManager = true;

    @Override
    public void processTicket() {
        
    }
}
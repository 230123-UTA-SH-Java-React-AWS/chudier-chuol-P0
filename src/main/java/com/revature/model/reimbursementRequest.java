package com.revature.model;

public class reimbursementRequest {
    
    private int amount;
    private String description;
    
    boolean pending = true;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    void submitTicket() {

    }
    
}
package com.revature.model;

public class Ticket {
    
    private int employee_id;
    private int amount;
    private String description;
    private String status;

    @Override
    public String toString() {
        return "Ticket [employee_id=" + employee_id + ", amount=" + amount + ", description=" + description + ", status="
                + status + "]";
    }

    public int getTicket_id() {
        return employee_id;
    }

    public void setTicket_id(int employee_id) {
        this.employee_id = employee_id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
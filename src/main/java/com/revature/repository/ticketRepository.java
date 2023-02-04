package com.revature.repository;

import com.revature.model.Ticket;

public class ticketRepository {
    
    public void saveTicket(Ticket ticket) {
        //save ticket data into the tickets database using that data retrieved from the user input.

        String sql = "insert into tickets (amount, description, status, employeeuser_id) values (?, ?, ?, ?) ";

    }
}
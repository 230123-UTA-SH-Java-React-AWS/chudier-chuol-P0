package com.revature.service;

import java.util.List;

import com.revature.model.Ticket;
import com.revature.repository.ticketRepository;

public class ticketService {
    
    public List<Ticket> submitTicket() {
        //collects and sends input information to the ticketRepository method. 

        ticketRepository repo = new ticketRepository();

        return repo.viewTickets();
    }
}
package com.revature.service;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Ticket;
import com.revature.repository.ticketRepository;

public class ticketService {
    
    private final ticketRepository repo = new ticketRepository();
    private final ObjectMapper mapper = new ObjectMapper();
    
    
}
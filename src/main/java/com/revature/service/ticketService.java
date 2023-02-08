package com.revature.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Ticket;
import com.revature.repository.ticketRepository;

public class ticketService {
    
    private final ticketRepository repo = new ticketRepository();
    private final ObjectMapper mapper = new ObjectMapper();
    
    public void submitTicket(String ticketJson) {
        //collects and sends input information to the ticketRepository method. 
        try {
            
            Ticket newTicket = mapper.readValue(ticketJson, Ticket.class);

            repo.saveTicket(newTicket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAllTickets() {
        List<Ticket> listOfTicks = repo.getAllTickets();
        
        String ticketJson = "";

        try {
            ticketJson = mapper.writeValueAsString(listOfTicks);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return ticketJson;
    }
}
package com.revature.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Ticket;
import com.revature.repository.authRepository;
import com.revature.repository.ticketRepository;

public class ticketService {
    
    private final ticketRepository repo = new ticketRepository();
    private final ObjectMapper mapper = new ObjectMapper();
    private final authRepository empRep = new authRepository();
    
    public String submitTicket(String ticketJson) {
        //collects and sends input information to the ticketRepository method. 
        try {
            
            Ticket newTicket = mapper.readValue(ticketJson, Ticket.class);
            Double i = new Double(newTicket.getAmount());
            if (i.compareTo(new Double("0")) > 0) {
                if (empRep.getAllEmployee().contains(newTicket.getEmail())) {
                    repo.saveTicket(newTicket);
                    return "Ticket submitted succesfully";
                } else {
                    return "Oops, you must be a registered employee to submit ticket";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Wrong entry, please make sure the amount is over 0";
    }

    public String getPendingTickets() {
        List<Ticket> listOfTickets = repo.getPendingTickets();

        String ticketsJson = "";

        try {
            ticketsJson = mapper.writeValueAsString(listOfTickets);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return ticketsJson;
    }


}
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
    
    private final ticketRepository ticketRepo = new ticketRepository();
    private final authRepository empRepo = new authRepository();
    private final ObjectMapper mapper = new ObjectMapper();
    
    public String submitTicket(String ticketJson) {
        //collects and sends input information to the ticketRepository method. 
        try {
            
            Ticket newTicket = mapper.readValue(ticketJson, Ticket.class);
            Double amount = new Double(newTicket.getAmount());
            if (amount.compareTo(new Double("0")) > 0) {
                if (empRepo.getAllEmployee().contains(newTicket.getEmail())) {
                    ticketRepo.saveTicket(newTicket);
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

    public String getPendingTickets(String eJson) {
        String pendTickets = "";

        try {
            JsonNode tNode = mapper.readTree(eJson);
            String emailJson = tNode.get("email").asText();

            if (empRepo.checkManagerStatus(emailJson)) {
                List<Ticket> listOfTickets = ticketRepo.getPendingTickets();
                
                pendTickets = mapper.writeValueAsString(listOfTickets);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return pendTickets;
    }

    public String processTicket(String ticketJson) {
        
        try {
            JsonNode jnode = mapper.readTree(ticketJson);

            String nodeEmail = jnode.get("email").asText();

            if(empRepo.checkManagerStatus(nodeEmail)) {
                ticketRepo.updateStatus(jnode);
                return "Ticket was updated by manager";
            } else {
                return "Oops, only a manager can update a ticket ";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Authorization denied";
    }

    public String personalTickets(String tString) {
        String personalTickets = "";
        
        try {
            JsonNode jNode = mapper.readTree(tString);

            if(jNode.findValue("status") == null) {
                List<Ticket> tickets = ticketRepo.getPesonalTickets(jNode);
                personalTickets = mapper.writeValueAsString(tickets);
            } else {
                List<Ticket> tickets = ticketRepo.getStatusTickets(jNode);
                personalTickets = mapper.writeValueAsString(tickets);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        return personalTickets;
    }

}
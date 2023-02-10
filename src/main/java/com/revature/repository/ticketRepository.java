package com.revature.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;

import com.revature.model.Ticket;
import com.revature.utils.connectionUtil;

public class ticketRepository {
    
    public Ticket saveTicket(Ticket ticket) {
        //save ticket data into the tickets database using that data retrieved from the user input.

        String sql = "insert into tickets (amount, description, email) values (?, ?, ?) ";

        try (Connection con = connectionUtil.getConnection()) {
            
            PreparedStatement prepstmt = con.prepareStatement(sql);

            prepstmt.setDouble(1, ticket.getAmount());
            prepstmt.setString(2, ticket.getDescription());
            prepstmt.setString(3, ticket.getEmail());
            
            prepstmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public List<Ticket> getPendingTickets() {
        
        String sql = "select * from tickets where status = ?";

        List<Ticket> listOfTickets = new ArrayList<>();

        try (Connection con = connectionUtil.getConnection()) {
            
            PreparedStatement prepstmt = con.prepareStatement(sql);

            ResultSet rs = prepstmt.executeQuery();

            while(rs.next()) {
                Ticket newTicket = new Ticket();

                newTicket.setId(rs.getInt(1));
                newTicket.setAmount(rs.getInt(2));
                newTicket.setDescription(rs.getString(3));
                newTicket.setStatus(rs.getString(4));
                newTicket.setEmail(rs.getString(5));
    
                listOfTickets.add(newTicket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOfTickets;
    }

    public void updateStatus(JsonNode treeNode){
        
        String sql = "update tickets set status = ? where ticketID = ?";

            try(Connection con = connectionUtil.getConnection()) {
                
                PreparedStatement prepstmt = con.prepareStatement(sql);

                prepstmt.setString(1, treeNode.get("status").asText());
                prepstmt.setInt(2, treeNode.get("ticketID").asInt());

                prepstmt.execute();

            } catch (Exception e) {
                
                e.printStackTrace();
            }
    }

}
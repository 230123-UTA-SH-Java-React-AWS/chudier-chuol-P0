package com.revature.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Ticket;
import com.revature.utils.connectionUtil;

public class ticketRepository {
    
    public void saveTicket(Ticket ticket) {
        //save ticket data into the tickets database using that data retrieved from the user input.

        String sql = "insert into tickets (amount, description, employee_id) values (?, ?, ?) ";

        try (Connection con = connectionUtil.getConnection()) {
            
            PreparedStatement prepstmt = con.prepareStatement(sql);

            prepstmt.setInt(1, ticket.getAmount());
            prepstmt.setString(2, ticket.getDescription());
            prepstmt.setInt(3, ticket.getEmployee_id());
            
            prepstmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception was thrown in the ticket repository package agent Chudier");
        }
    }

    // public List<Ticket> viewTickets() {
        
    //     String sql = "select * from tickets";

    //     List<Ticket> listOfTickets = new ArrayList<>();

    //     try (Connection con = connectionUtil.getConnection()) {
            
    //         Statement stmt = con.createStatement();

    //         ResultSet rs = stmt.executeQuery(sql);

    //         //mapping information from a table to a data structure instead

    //         while (rs.next()) {
    //             Ticket newTicket = new Ticket();

    //             newTicket.setTicket_id(rs.getInt("employeeuser_id"));
    //             newTicket.setAmount(rs.getInt("Amount"));
    //             newTicket.setDescription(rs.getString("Description"));
    //             newTicket.setStatus(rs.getString("Status"));

    //             listOfTickets.add(newTicket);
    //         }

    //     } catch (Exception e) {
    //         e.printStackTrace();
            
    //     }
        
    //     return listOfTickets;
    // }
}
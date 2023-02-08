package com.revature;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

import com.sun.net.httpserver.HttpServer;

import com.revature.controllers.*;
import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.repository.authRepository;
import com.revature.repository.ticketRepository;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server is running Agent Chudier");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/employee", new employeeController());
        server.createContext("/employee/tickets", new ticketController());
        
        server.setExecutor(null);
        server.start();

        // ticketRepository tickets = new ticketRepository();

        // List<Ticket> getTickets = tickets.getAllTickets();

        // System.out.print(getTickets);

    }
}

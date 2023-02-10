package com.revature.controllers;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.service.ticketService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ticketController implements HttpHandler {
    ticketService tickService = new ticketService();
    ObjectMapper mapper = new ObjectMapper();
    

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String httpVerb = exchange.getRequestMethod();

        switch (httpVerb) {
            case "POST":
                postRequest(exchange);
                break;
            case "GET":
                getRequest(exchange);
                break;
            case "PUT":
                putRequest(exchange);
                break;
            default:
                
        }
    }

    public void getRequest(HttpExchange exchange) throws IOException {
        String statuscode = "Oops, you are not authorized to make this request";
        
        
        InputStream is = exchange.getRequestBody();

        StringBuilder textBuilder = new StringBuilder();

        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int letter = 0;

            while ((letter = reader.read()) != -1) {
                textBuilder.append((char)letter);
            }
        }

        String ticketList = tickService.getPendingTickets(textBuilder.toString());

        if(ticketList == null) {
            OutputStream os = exchange.getResponseBody();
            os.write(statuscode.getBytes());
            os.close();
        }

        exchange.sendResponseHeaders(200, ticketList.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(ticketList.getBytes());
        os.close();
    }

    public void postRequest(HttpExchange exchange) throws IOException {
        String newTicket;
        
        InputStream is = exchange.getRequestBody();

        StringBuilder textBuilder = new StringBuilder();
        
        //converts our binary to letters
        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int letter = 0;

            while ((letter = reader.read()) != -1) {
                textBuilder.append((char)letter);
            }
        }
        
        System.out.println(textBuilder.toString());
        
        newTicket = tickService.submitTicket(textBuilder.toString());
        
        exchange.sendResponseHeaders(200, newTicket.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(newTicket.getBytes());
        os.close();
    }

    public void putRequest (HttpExchange exchange) throws IOException {
        String ticketUpdated;
        
        InputStream is = exchange.getRequestBody();

        StringBuilder textBuilder = new StringBuilder();

        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int letter = 0;

            while ((letter = reader.read()) != -1) {
                textBuilder.append((char)letter);
            }
        }

        ticketUpdated = tickService.processTicket(textBuilder.toString());
        
        exchange.sendResponseHeaders(200, ticketUpdated.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(ticketUpdated.getBytes());
        os.close();

    }
}
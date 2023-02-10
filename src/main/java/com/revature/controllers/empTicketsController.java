package com.revature.controllers;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.service.ticketService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class empTicketsController implements HttpHandler {
    ticketService tickService = new ticketService();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String httpVerb = exchange.getRequestMethod();

        switch (httpVerb) {
            case "POST":
                break;
            case "GET":
                getRequest(exchange);
                break;
            case "PUT":
                break;
            default:
                
        }
    }

    public void getRequest(HttpExchange exchange) throws IOException {
        
        InputStream is = exchange.getRequestBody();

        StringBuilder textBuilder = new StringBuilder();

        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int letter = 0;

            while ((letter = reader.read()) != -1) {
                textBuilder.append((char)letter);
            }
        }

        String ticketList = tickService.personalTickets(textBuilder.toString());

        exchange.sendResponseHeaders(200, ticketList.getBytes().length);
        
        OutputStream os = exchange.getResponseBody();
        os.write(ticketList.getBytes());
        os.close();
    }

}
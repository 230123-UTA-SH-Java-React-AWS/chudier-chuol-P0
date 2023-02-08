package com.revature.controllers;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.service.ticketService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ticketController implements HttpHandler {
    ticketService service = new ticketService();

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String httpVerb = exchange.getRequestMethod();

        switch (httpVerb) {
            case "POST":
                postRequest(exchange);
                break;
            case "GET":
                break;
            default:
                
        }
    }

    public void postRequest(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();

        StringBuilder textBuilder = new StringBuilder();
        
        //converts our binary to letters
        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int letter = 0;

            while ((letter = reader.read()) != -1) {
                textBuilder.append((char)letter);
            }
        }
        // {
        //     "amount": 321,
        //     "description": "ski trip",
        //     "employee_id": 3
        // }

        System.out.println(textBuilder.toString());
        
        exchange.sendResponseHeaders(200, textBuilder.toString().getBytes().length);

        service.submitTicket(textBuilder.toString());

        OutputStream os = exchange.getResponseBody();
        os.write(textBuilder.toString().getBytes());
        os.close();
    }    
}
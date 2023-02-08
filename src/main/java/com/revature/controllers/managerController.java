package com.revature.controllers;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Manager;
import com.revature.service.managerService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class managerController implements HttpHandler {
    
    managerService service = new managerService();
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
            default:
        }
    }

    public void getRequest(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();

        StringBuilder textBuilder = new StringBuilder();

        Manager foundUser = null;

        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int letter = 0;

            while ((letter = reader.read()) != -1) {
                textBuilder.append((char)letter);
            }
        }

        System.out.println(textBuilder.toString());

        // exchange.sendResponseHeaders(200, textBuilder.toString().getBytes().length);

        foundUser = service.loginManager(textBuilder.toString());
        
        if(foundUser == null) {
            exchange.sendResponseHeaders(403, 0);
        } else {
            exchange.sendResponseHeaders(200, 0);
        }

        OutputStream os = exchange.getResponseBody();
        // os.write(textBuilder.toString().getBytes());
        os.close();
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

        System.out.println(textBuilder.toString());
        
        exchange.sendResponseHeaders(200, textBuilder.toString().getBytes().length);

        
        service.managerRegister(textBuilder.toString());

        OutputStream os = exchange.getResponseBody();
        os.write(textBuilder.toString().getBytes());
        os.close();

    }    
}
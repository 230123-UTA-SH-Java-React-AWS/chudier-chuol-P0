package com.revature.controllers;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.service.authService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class employeeController implements HttpHandler {

    authService service = new authService();

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

    // public void getRequest(HttpExchange exchange) throws IOException {
    //     authService service = new authService();

    //     String jsonCurrentList = service.getAllEmployee();

    //     exchange.sendResponseHeaders(200, jsonCurrentList.getBytes().length);

    //     OutputStream os = exchange.getResponseBody();
    //     os.write(jsonCurrentList.getBytes());
    //     os.close();
    // }

    public void getRequest(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();

        StringBuilder textBuilder = new StringBuilder();

        Employee foundUser = null;

        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int letter = 0;

            while ((letter = reader.read()) != -1) {
                textBuilder.append((char)letter);
            }
        }

        System.out.println(textBuilder.toString());

        // exchange.sendResponseHeaders(200, textBuilder.toString().getBytes().length);

        foundUser = service.loginEmployee(textBuilder.toString());
        
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

        // System.out.println(textBuilder.toString());
        
        exchange.sendResponseHeaders(200, textBuilder.toString().getBytes().length);

        
        service.employeeRegister(textBuilder.toString());

        OutputStream os = exchange.getResponseBody();
        os.write(textBuilder.toString().getBytes());
        os.close();

    }    
}
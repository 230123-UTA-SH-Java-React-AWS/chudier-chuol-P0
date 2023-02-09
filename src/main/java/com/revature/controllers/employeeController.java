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

    authService employeeService = new authService();
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

        // Employee foundUser = null;

        try(Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int letter = 0;

            while ((letter = reader.read()) != -1) {
                textBuilder.append((char)letter);
            }
        }

        System.out.println(textBuilder.toString());

        String message = "The email or password you entered is invalid";
        
        if(employeeService.loginEmployee(textBuilder.toString())!= true) {
            
            exchange.sendResponseHeaders(403, message.getBytes().length);

        } else {
            message = "Login was succesful";
            exchange.sendResponseHeaders(200, message.getBytes().length);

        }
        
        OutputStream os = exchange.getResponseBody();
        os.write(message.getBytes());
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

        String registerResponse = employeeService.employeeRegister(textBuilder.toString());
        
        // exchange.sendResponseHeaders(200, textBuilder.toString().getBytes().length);
        exchange.sendResponseHeaders(200, registerResponse.getBytes().length);
        

        
        OutputStream os = exchange.getResponseBody();
        os.write(registerResponse.getBytes());
        os.close();

    }    
}
package com.revature;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import com.revature.controllers.*;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server is running Agent Chudier");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/employee", new employeeController());
        
        server.setExecutor(null);
        server.start();
    }
}

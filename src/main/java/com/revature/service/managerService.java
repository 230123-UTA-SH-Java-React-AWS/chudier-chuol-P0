package com.revature.service;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Manager;
import com.revature.repository.managerRepository;

public class managerService {
    
    private final managerRepository repo = new managerRepository();
    private final ObjectMapper mapper = new ObjectMapper();
    
    public void managerRegister(String managerJson) {
        
        try {
           Manager newManager = mapper.readValue(managerJson, Manager.class);

           repo.saveManager(newManager);

        } catch(JsonParseException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Manager loginManager(String currentUserJson) {
        //collects and sends user input to the login repo
        Manager foundUser = null;
        try {
            Manager currentUser = mapper.readValue(currentUserJson, Manager.class);

            foundUser = repo.loginManager(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundUser;
    }
    
}
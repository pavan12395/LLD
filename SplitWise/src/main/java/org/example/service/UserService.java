package org.example.service;


import org.example.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String,User> userMap;

    private static UserService instance;

    public UserService(){
        userMap = new HashMap<>();
    }

    public static UserService getInstance() {
        synchronized (UserService.class){
            if(instance == null){
                synchronized (UserService.class){
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public static void setInstance(UserService UserService){
        synchronized (UserService.class){
            instance = UserService;
        }
    }
    public boolean contains(String userID){
        return userMap.containsKey(userID);
    }

    public void addUser(User user) throws  Exception {
        if(userMap.containsKey(user.getId())){
            throw new Exception("User already exists");
        }
        else {
            userMap.put(user.getId(),user);
        }
    }
}
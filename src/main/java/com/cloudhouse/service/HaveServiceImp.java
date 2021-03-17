package com.cloudhouse.service;

import com.cloudhouse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HaveServiceImp implements HaveService{
    @Autowired
    private UserServiceImp userServiceImp;
    public void haveUser(User user){
        User result = userServiceImp.query(user);
        if(result == null){
            userServiceImp.insertUser(user);
        }
    }
}

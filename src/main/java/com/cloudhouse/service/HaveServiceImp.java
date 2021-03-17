package com.cloudhouse.service;

import com.cloudhouse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HaveServiceImp implements HaveService{
    @Autowired
    private UserServiceImp userServiceImp;

    //判断是否已经数据库有user，并且是否需要更新wechat
    public void haveUser(User user){
        User result = userServiceImp.query(user);
        if(result == null){
            userServiceImp.insertUser(user);
        }else if(result != null){
            if(result.getWeChat() != user.getWeChat()){
                userServiceImp.updateUserWeChat(user);
            }
        }
    }
}

package com.cloudhouse.service;

import com.cloudhouse.mapper.UserMapper;
import com.cloudhouse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserMapper userMapper;

    public User query(User user){
        return userMapper.queryByUser(user);
    }
    public int insertUser(User user){
        return userMapper.insertUser(user);
    }
    public int updateUserChances(User user){
        return userMapper.updateUserChances(user);
    }
    public int delete(User user){
        return userMapper.deleteUser(user);
    }
    public int updateAllChances(int chances){
        return userMapper.updateAllChances(chances);
    }

    @Override
    public int updateUserWeChat(User user) {
        return userMapper.updateUserWeChat(user);
    }
}

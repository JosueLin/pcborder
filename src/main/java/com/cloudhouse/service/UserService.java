package com.cloudhouse.service;

import com.cloudhouse.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User query(User user);
    int insertUser(User user);
    int delete(User user);
    int updateUserChances(User user);
    int updateAllChances(int chances);
    int updateUserWeChat(User user);
}

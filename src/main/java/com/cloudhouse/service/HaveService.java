package com.cloudhouse.service;

import com.cloudhouse.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface HaveService {
    void haveUser(User user);
}

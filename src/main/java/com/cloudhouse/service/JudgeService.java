package com.cloudhouse.service;

import com.cloudhouse.pojo.MessageToFront;
import com.cloudhouse.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface JudgeService {
    MessageToFront judgeService(User user);
}

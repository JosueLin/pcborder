package com.cloudhouse.controller;

import com.cloudhouse.pojo.MessageToFront;
import com.cloudhouse.pojo.User;
import com.cloudhouse.service.HaveServiceImp;
import com.cloudhouse.service.JudgeServiceImp;
import com.cloudhouse.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class JudgeController {
    @Autowired
    private User user;
    @Autowired
    private JudgeServiceImp judgeServiceImp;
    @Autowired
    private HaveServiceImp haveServiceImp;
    @PostMapping(value = "/order",produces = "application/json;charset=UTF-8")
    public MessageToFront judge(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String studentID = httpServletRequest.getParameter("studentID");
        String studentName = httpServletRequest.getParameter("studentName");
        String college = httpServletRequest.getParameter("college");
        String weChat = httpServletRequest.getParameter("weChat");
        String sizeA = httpServletRequest.getParameter("sizeA");
        String sizeB = httpServletRequest.getParameter("sizeB");
        String numberOfLayers = httpServletRequest.getParameter("numberOfLayers");

        user.setStudentID(studentID);
        user.setStudentName(studentName);
        user.setCollege(college);
        user.setWeChat(weChat);
        user.setSizeA(sizeA);
        user.setSizeB(sizeB);
        user.setNumberOfLayers(numberOfLayers);

        haveServiceImp.haveUser(user);
        MessageToFront result = judgeServiceImp.judgeService(user);

        return result;
    }

}

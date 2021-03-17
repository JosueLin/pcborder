package com.cloudhouse.service;

import com.cloudhouse.mapper.MonthChancesMapper;
import com.cloudhouse.pojo.MessageToFront;
import com.cloudhouse.pojo.MonthChances;
import com.cloudhouse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgeServiceImp implements JudgeService{
    @Autowired
    private MailServiceImp mailServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private MonthChancesMapper monthChancesMapper;
    @Autowired
    private MessageToFront messageToFront;
    @Autowired
    private FlushServiceImp flushServiceImp;
    @Autowired
    private MonthChances monthChances;

    public MessageToFront judgeService(User user){
        //判断是否月初，更新数据
        flushServiceImp.flushMonthData();

        int s4_1 = 0;
        int s4_2 = 0;
        //n为整个月的预约次数
        int n = monthChancesMapper.query().getTotalChances();
        if((Integer.parseInt(user.getSizeA()) < 10) && (Integer.parseInt(user.getSizeB()) < 10)){
            s4_1 = 1;
        }
        if(Integer.parseInt(user.getNumberOfLayers()) == 1){
            s4_2 = 1;
        }

        if((userServiceImp.query(user)).getChances() <= 0){
            messageToFront.setResult("false");
            messageToFront.setMessage("对不起，您在本月的预约次数已使用完毕！");
            return messageToFront;
        }

        n--;
        monthChances.setId(1);
        monthChances.setTotalChances(n);
        monthChancesMapper.update(monthChances);
        if(s4_1 == 1 && s4_2 == 1){
            //数据库减次数
            user.setChances((userServiceImp.query(user)).getChances()-1);
            userServiceImp.updateUserChances(user);
            //发邮件
            String subject = "PCBOrder" + user.getStudentID() + user.getStudentName();
            String content = "studentID:" + user.getStudentID() + "\n" +
                    "studentName:" + user.getStudentName() + "\n" +
                    "college:" + user.getCollege() + "\n" +
                    "wechat:" + user.getWeChat() + "\n" +
                    "size:" + user.getSizeA() + "*" + user.getSizeB() + "\n" +
                    "numberoflayers:" + user.getNumberOfLayers();
            mailServiceImp.sendSimpleMail("3246560243@qq.com",subject,content);


            messageToFront.setResult("true");
            messageToFront.setMessage("预约成功");
            return messageToFront;
        }
        else {
            if(s4_1 == 0){
                //数据库减次数
                user.setChances((userServiceImp.query(user)).getChances()-1);
                userServiceImp.updateUserChances(user);
                //发邮件
                String subject = "PCBOrder" + user.getStudentID() + user.getStudentName();
                String content = "studentID:" + user.getStudentID() + "\n" +
                        "studentName:" + user.getStudentName() + "\n" +
                        "college:" + user.getCollege() + "\n" +
                        "wechat:" + user.getWeChat() + "\n" +
                        "size:" + user.getSizeA() + "*" + user.getSizeB() + "\n" +
                        "numberoflayers:" + user.getNumberOfLayers();
                mailServiceImp.sendSimpleMail("3246560243@qq.com",subject,content);

                messageToFront.setResult("true");
                messageToFront.setMessage("已提交请求，您需要制作的PCB尺寸过大，请自带板材或等工作人员联系你！");
                return messageToFront;
            }

        }
        //数据库减次数
        user.setChances((userServiceImp.query(user)).getChances()-1);
        userServiceImp.updateUserChances(user);
        //发邮件
        String subject = "PCBOrder" + user.getStudentID() + user.getStudentName();
        String content = "studentID:" + user.getStudentID() + "\n" +
                "studentName:" + user.getStudentName() + "\n" +
                "college:" + user.getCollege() + "\n" +
                "wechat:" + user.getWeChat() + "\n" +
                "size:" + user.getSizeA() + "*" + user.getSizeB() + "\n" +
                "numberoflayers:" + user.getNumberOfLayers();
        mailServiceImp.sendSimpleMail("3246560243@qq.com",subject,content);

        messageToFront.setResult("true");
        messageToFront.setMessage("预约成功");
        return messageToFront;
    }
}

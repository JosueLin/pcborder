package com.cloudhouse.service;

import com.cloudhouse.mapper.MonthChancesMapper;
import com.cloudhouse.mapper.UserMapper;
import com.cloudhouse.pojo.MonthChances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class FlushServiceImp implements FlushService{
    @Autowired
    private MonthChancesMapper monthChancesMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MonthChances monthChances;
    @Override
    public boolean flushMonthData() {
        if(checkNewMonth()){
            monthChances.setTotalChances(12);
            monthChances.setId(1);
            monthChancesMapper.update(monthChances);

            userMapper.updateAllChances(2);
            //表示是月初，成功更新数据
            return true;
        }
        //表示不是月初，没更新数据
        return false;
    }

    @Override
    public boolean checkNewMonth() {
        int day_of_month = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if(day_of_month == 1){
            return true;
        }
        return false;
    }
}

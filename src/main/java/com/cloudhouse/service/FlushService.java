package com.cloudhouse.service;

import org.springframework.stereotype.Service;

//用来检测更新每月的数据库，新的一个月，预约次数刷新
@Service
public interface FlushService {
    boolean flushMonthData();
    boolean checkNewMonth();
}

package com.cloudhouse.service;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class FlushServiceImpTest {

    @Test
    void flushMonthData() {
        int day_of_month = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        System.out.println(day_of_month);
    }
}
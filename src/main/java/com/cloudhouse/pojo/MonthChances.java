package com.cloudhouse.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MonthChances {
    private int id;
    private int totalChances;
}

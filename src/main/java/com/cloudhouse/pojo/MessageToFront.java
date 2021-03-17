package com.cloudhouse.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageToFront {
    private String result;
    private String message;
}

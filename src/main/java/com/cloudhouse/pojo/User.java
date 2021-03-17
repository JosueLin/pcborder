package com.cloudhouse.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private int id;
    private String studentID;
    private String studentName;
    private String college;
    private String weChat;
    private String sizeA;
    private String sizeB;
    private String numberOfLayers;
    private int chances;
}

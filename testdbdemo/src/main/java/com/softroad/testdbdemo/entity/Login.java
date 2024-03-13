package com.softroad.testdbdemo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Login {

    private String loginid;
    private String loginpd;
    private String syozokucd;
    private String menuid;
    private String printip;
    private String statusflg;
    private Date uptime;


    
    
}

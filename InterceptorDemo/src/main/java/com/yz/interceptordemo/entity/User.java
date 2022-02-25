package com.yz.interceptordemo.entity;

import lombok.*;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String sex;
    private Integer age;
    /*
     * 类上面的注解是使用的lombok插件，不知道的朋友可自行查阅资料
     * */

}

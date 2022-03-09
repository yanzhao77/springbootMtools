package com.yz.webdemo.service;


import com.yz.webdemo.entity.RoleInfo;
import com.yz.webdemo.entity.Users;

import java.util.List;



public interface UserServiceI{
    List<Users> getAllUser();

    Users getUserById(int id);

    int createUser(Users user);

    int deleteById(int id);

    int updateById(String name, String password, String id);
    
    Users getUserByData(String name, String password);
    
    RoleInfo getRolesById(int id);
    
    int clearQtempByUserId(int id);
}

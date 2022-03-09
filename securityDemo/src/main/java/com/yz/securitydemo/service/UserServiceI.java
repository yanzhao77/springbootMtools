package com.yz.securitydemo.service;


import com.yz.securitydemo.entity.RoleInfo;
import com.yz.securitydemo.entity.Users;

import java.util.List;


public interface UserServiceI {
    List<Users> getAllUser();

    Users getUserById(int id);

    int createUser(Users user);

    int deleteById(int id);

    int updateById(String name, String password, String id);

    Users getUserByData(String name, String password);

    RoleInfo getRolesById(int id);

    int clearQtempByUserId(int id);
}

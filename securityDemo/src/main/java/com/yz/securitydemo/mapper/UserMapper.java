package com.yz.securitydemo.mapper;

import com.yz.securitydemo.entity.RoleInfo;
import com.yz.securitydemo.entity.Users;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<Users> {
 
    List<Users> getAllUser();

    Users getUserById(int id);

    int createUser(Users user);

    int deleteById(int id);
    
    Users  loadUserByUsername(String name);

    int updateById(String name, String password, String id);

    Users getUserByData(@Param("name") String name, @Param("password") String password);
    
    RoleInfo getRolesById(int id);
    
    List<String> selectMenusByUserId(Integer uid);
    
    int clearQtempByUserId(int id);
}

package com.yz.webdemo.mapper;

import java.util.List;

import com.yz.webdemo.entity.RoleInfo;
import com.yz.webdemo.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
 
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

package com.yz.securityjwtdemo.service.impl;


import com.yz.securityjwtdemo.entity.RoleInfo;
import com.yz.securityjwtdemo.entity.Users;
import com.yz.securityjwtdemo.mapper.UserMapper;
import com.yz.securityjwtdemo.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService {


    UserMapper userMapper;


    public List<Users> getAllUser() {
        return userMapper.getAllUser();
    }


    public Users getUserById(int id) {
        return userMapper.getUserById(id);
    }


    public int createUser(Users user) {
        return userMapper.createUser(user);
    }


    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }


    public int updateById(String name, String password, String id) {
        return userMapper.updateById(name, password, id);
    }


    public Users getUserByData(String name, String password) {
        return userMapper.getUserByData(name, password);
    }


    public RoleInfo getRolesById(int id) {
        // TODO Auto-generated method stub
        return userMapper.getRolesById(id);
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("--------------loadUserByUsername----------------");
        // TODO Auto-generated method stub
        Users user = userMapper.loadUserByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }
        user.setRoleInfo(userMapper.getRolesById(user.getId()));
        List<String> menusList = userMapper.selectMenusByUserId(user.getId());
        // 配置菜单权限
        String menusArr = "";

        for (String menus : menusList) {
            String roleStr = "ROLE_" + menus;
            AuthorityUtils.commaSeparatedStringToAuthorityList(roleStr);
            menusArr = StringUtils.isEmpty(menusArr) ? roleStr : menusArr + "," + roleStr;
        }
        user.setMenuus(menusArr);
        return user;
    }


    public int clearQtempByUserId(int id) {
        // TODO Auto-generated method stub
        return userMapper.clearQtempByUserId(id);
    }


}

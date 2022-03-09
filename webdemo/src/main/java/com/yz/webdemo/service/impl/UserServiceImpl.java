package com.yz.webdemo.service.impl;

import com.yz.webdemo.entity.RoleInfo;
import com.yz.webdemo.entity.Users;
import com.yz.webdemo.mapper.UserMapper;
import com.yz.webdemo.service.UserServiceI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserServiceI, UserDetailsService {

    @Autowired
    UserMapper userMapper;


    @Override
    public List<Users> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public Users getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int createUser(Users user) {
        return userMapper.createUser(user);
    }

    @Override
    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int updateById(String name, String password, String id) {
        return userMapper.updateById(name, password, id);
    }

    @Override
    public Users getUserByData(String name, String password) {
        return userMapper.getUserByData(name, password);
    }

    @Override
    public RoleInfo getRolesById(int id) {
        // TODO Auto-generated method stub
        return userMapper.getRolesById(id);
    }

    @Override
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

    @Override
    public int clearQtempByUserId(int id) {
        // TODO Auto-generated method stub
        return userMapper.clearQtempByUserId(id);
    }

}

package com.yz.postgresqlbackupdemo.service.impl;

import com.yz.postgresqlbackupdemo.entity.Users;
import com.yz.postgresqlbackupdemo.mapper.PostgreMapper;
import com.yz.postgresqlbackupdemo.service.PostgreServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/22 17:28
 */
@Service
public class PostgreServiceImpl implements PostgreServiceI {
    @Autowired
    PostgreMapper postgreMapper;

    @Override
    public void runScript(String filePath) {


    }

    @Override
    public void backupScript(String filePath) {

    }

    @Override
    public void restoreScript(String filePath) {

    }

    @Override
    public List<Users> getAllUser() {
//        return postgreMapper.getAllUser();
    return null;
    }
}

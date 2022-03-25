package com.yz.postgresqlbackupdemo.service;

import com.yz.postgresqlbackupdemo.entity.Users;

import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/22 17:26
 */
public interface PostgreServiceI {
    void runScript(String filePath);

    void backupScript(String filePath);

    void restoreScript(String filePath);

    List<Users> getAllUser();
}

package com.yz.webdemo.service;


import com.yz.webdemo.entity.RoleInfo;
import com.yz.webdemo.entity.Users;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public interface CommonServiceI {

    /**
     * ロックされたテーブルがあるかどうかを照会します
     *
     * @return
     */
    List<LinkedHashMap<String, Object>> checkAllLocks();
}

package com.yz.webdemo.service.impl;

import com.yz.webdemo.mapper.CommonMapper;
import com.yz.webdemo.service.CommonServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonServiceI {
    @Autowired
    CommonMapper commonMapper;

    /**
     * ロックされたテーブルがあるかどうかを照会します
     */
    @Override
    public List<LinkedHashMap<String, Object>> checkAllLocks() {
        List<LinkedHashMap<String, Object>> allLocks = commonMapper.selectAllLocks();
        if (null != allLocks && allLocks.size() > 0) {
            for (int i = 0; i < allLocks.size(); i++) {
                Map<String, Object> stringObjectMap = allLocks.get(i);
                String tableName = stringObjectMap.get("relname").toString();
                String mode = stringObjectMap.get("mode").toString();
                String locktype = stringObjectMap.get("locktype").toString();
                String oid = stringObjectMap.get("oid").toString();
                System.out.println("lockTable: " + tableName + "\t\t" + "mode: " + mode + "\t\t" + "lockType: " + locktype + "\t\t" + "oid: " + oid);

            }
        }
        return allLocks;
    }
}

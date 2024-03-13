package com.yz.curddemo.bean;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import jakarta.annotation.Resource;

/**
 * @author yanzhao
 * @version 1.0
 * @classname CommonDao
 * @date 2023/06/26 17:17
 * @description TODO
 */
public class CommonDao extends SqlSessionDaoSupport
{
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}

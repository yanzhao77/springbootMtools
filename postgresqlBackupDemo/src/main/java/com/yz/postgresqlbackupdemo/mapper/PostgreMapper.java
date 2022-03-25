package com.yz.postgresqlbackupdemo.mapper;

import com.yz.postgresqlbackupdemo.entity.Users;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/22 17:29
 */
public interface PostgreMapper extends Mapper<Users> {

    Users getAllUser();

    int truncateTable(String tablename);
}

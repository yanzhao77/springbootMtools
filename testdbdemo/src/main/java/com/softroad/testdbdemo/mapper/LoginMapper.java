package com.softroad.testdbdemo.mapper;


import com.softroad.testdbdemo.entity.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LoginMapper {

    List<Login> selectByKeys(@Param("username") String profname, @Param("password") String profpassword);

    List<Login> selectAllUser();

    String selectMaxSeqNo();
}

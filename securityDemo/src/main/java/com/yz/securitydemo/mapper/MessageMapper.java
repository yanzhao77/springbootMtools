package com.yz.securitydemo.mapper;

import java.util.List;

import com.yz.securitydemo.entity.Message;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MessageMapper extends Mapper<Message> {

	List<Message> selectByid(@Param("id") Integer id);

	List<Message> getMessage(@Param("id") Integer id);

}

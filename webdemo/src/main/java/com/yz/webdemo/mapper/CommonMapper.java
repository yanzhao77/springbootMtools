package com.yz.webdemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/1 16:20
 */
@Mapper
public interface CommonMapper {


    /**
     * ロックされたテーブルがあるかどうかを照会します
     *
     * @return
     */
    List<LinkedHashMap<String, Object>> selectAllLocks();
}

package com.zhongan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ServerUrlDao{
	
    List<Map<String, Object>> selectServerUrls(@Param("ckey") String ckey);

}

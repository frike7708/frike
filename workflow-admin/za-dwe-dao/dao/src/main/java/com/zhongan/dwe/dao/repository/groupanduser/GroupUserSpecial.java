package com.zhongan.dwe.dao.repository.groupanduser;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GroupUserSpecial {
	
	public List<Map<String,Object>> selectUserList(@Param("groupId") String groupId);
	
	public List<Map<String,Object>> otherUserList(@Param("groupId") String groupId);

	public List<Map<String,Object>> getMembership(@Param("groupId") String groupId,@Param("userId") String userId);
	
	public int insert(@Param("groupId") String groupId,@Param("userId") String userId);

	public int deleteByGroupID(@Param("groupId") String groupId);

}

package com.zhongan.dwe.dao.service;


import java.util.List;
import java.util.Map;

import com.zhongan.dwe.dao.dto.ActIdUserDTO;

public interface GroupAndUsersService {
	
	public List<Map<String, Object>> selectGroupUserList(String groupId);
	
	public List<Map<String, Object>> otherGroupUserList(String groupId);
	
	public List<Map<String, Object>> getMembershipList(String groupId,String userId);
	
	public int add(String groupId,String userId);
	
	public int delete(String groupId);

}

package com.zhongan.dwe.system.service;

import com.zhongan.dwe.system.model.GroupDTO;

public interface IGroupService {
	
	public void insertGroupAndMembership(GroupDTO dto) throws Exception;

	public void delete(String id);

	public void addGroupUsers(GroupDTO dto);

	public void updateGroupName(GroupDTO dto);

}

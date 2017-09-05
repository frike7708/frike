package com.zhongan.dwe.dao.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongan.dsf.provider.annotation.Provider;
import com.zhongan.dwe.dao.repository.groupanduser.GroupUserSpecial;
import com.zhongan.dwe.dao.service.GroupAndUsersService;

@Provider
@Service
public class GroupAndUsersServiceImpl implements GroupAndUsersService {
	
    @Resource
    GroupUserSpecial specialDao;

	@Override
	public List<Map<String, Object>> selectGroupUserList(String groupId) {
		return specialDao.selectUserList(groupId);
	}

	@Override
	public List<Map<String, Object>> otherGroupUserList(String groupId) {
		return specialDao.otherUserList(groupId);
	}
	
	@Override
	public List<Map<String, Object>> getMembershipList(String groupId,String userId) {
		return specialDao.getMembership(groupId,userId);
	}

	@Override
	public int add(String groupId, String userId) {
		return specialDao.insert(groupId, userId);
	}

	@Override
	public int delete(String groupId) {
		return specialDao.deleteByGroupID(groupId);
	}	
	
	
}
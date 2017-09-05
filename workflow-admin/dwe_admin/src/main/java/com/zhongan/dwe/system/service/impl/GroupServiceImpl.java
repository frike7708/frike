package com.zhongan.dwe.system.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zhongan.dsf.consumer.annotation.Consumer;
import com.zhongan.dwe.dao.dto.ActIdGroupDTO;
import com.zhongan.dwe.dao.dto.ActIdMembershipDTO;
import com.zhongan.dwe.dao.service.ActIdGroupService;
import com.zhongan.dwe.dao.service.ActIdMembershipService;
import com.zhongan.dwe.dao.service.GroupAndUsersService;
import com.zhongan.dwe.system.model.GroupDTO;
import com.zhongan.dwe.system.service.IGroupService;

@Component
public class GroupServiceImpl implements IGroupService {
	
    protected final static Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

	
	@Consumer(connectTimeoutMillis=20000)
	private ActIdGroupService actIdGroupService;
	
	@Consumer(connectTimeoutMillis=20000)
	private ActIdMembershipService  membershipService;
	
	@Consumer
	private GroupAndUsersService groupUserService;
	
	@Override
	public void insertGroupAndMembership(GroupDTO dto) {
		String id = dto.getId();
		String name = dto.getName();
		List<String> users = dto.getUsers();
		ActIdGroupDTO byID_ = actIdGroupService.getByID_(id);
		if(byID_ != null){
			throw new RuntimeException("该用户组已经存在，请勿重复创建");
		}
		logger.info("添加新的用户组：ID=" + id);
		ActIdGroupDTO actidgroupDTO = new ActIdGroupDTO();
		actidgroupDTO.setId(id);
		actidgroupDTO.setName(name);
		actidgroupDTO.setRev(4);//默认
		actidgroupDTO.setType("assignment");//默认
		actIdGroupService.add(actidgroupDTO);
		logger.info("设置组员：ID=" + id + "  成员数==" + users.size());
		for (String userId : users) {
			List<Map<String, Object>> membership = groupUserService.getMembershipList(id, userId);
			if(membership != null && membership.size() > 0){
				continue;
			}
			groupUserService.add(id, userId);
		}
	}

	@Override
	public void delete(String id) {
		//删除关联关系
		groupUserService.delete(id);
		//删除用户组
		actIdGroupService.delete(id);
	}

	@Override
	public void addGroupUsers(GroupDTO dto) {
		String id = dto.getId();
		List<String> users = dto.getUsers();
		//删除当前groupId关联的所有用户
		groupUserService.delete(id);
		for (String userId : users) {
			groupUserService.add(id, userId);
		}
		
	}

	@Override
	public void updateGroupName(GroupDTO dto) {
		ActIdGroupDTO actidgroupDTO =  new ActIdGroupDTO();
		actidgroupDTO.setName(dto.getName());
		actIdGroupService.update(actidgroupDTO, dto.getId());
	}

}

package com.zhongan.dwe.dao.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zhongan.dsf.provider.annotation.Provider;
import com.zhongan.dwe.dao.biz.ActIdMembershipBiz;
import com.zhongan.dwe.dao.dto.ActIdMembershipDTO;
import com.zhongan.dwe.dao.repository.actidmembership.condition.ActIdMembershipWhere;
import com.zhongan.dwe.dao.service.ActIdMembershipService;


@Provider
@Service("actIdMembershipService")
public class ActIdMembershipServiceImpl implements ActIdMembershipService {

	@Autowired
	@Qualifier("actIdMembershipBiz")
	private ActIdMembershipBiz actIdMembershipBiz;

	@Override
	public void add(ActIdMembershipDTO actidmembershipDTO) {
		actIdMembershipBiz.add(actidmembershipDTO);
	}

	@Override
	public List<ActIdMembershipDTO> getMembership(String groupId,String userId){
		ActIdMembershipWhere where = new ActIdMembershipWhere();
		where.setGroupId(groupId);
		//where.setUserId(userId);
		List<ActIdMembershipDTO> findByCondition = actIdMembershipBiz.findByCondition(where);
		return findByCondition;
	}


}
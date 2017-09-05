package com.zhongan.dwe.dao.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageInfo;
import com.zhongan.base.repository.PageList;
import com.zhongan.base.repository.PageListImpl;
import com.zhongan.dwe.dao.biz.ActIdMembershipBiz;
import com.zhongan.dwe.dao.dataobject.ActIdMembership;
import com.zhongan.dwe.dao.dto.ActIdMembershipDTO;
import com.zhongan.dwe.dao.repository.actidmembership.ActIdMembershipRepository;
import com.zhongan.dwe.dao.repository.actidmembership.condition.ActIdMembershipSelect;
import com.zhongan.dwe.dao.repository.actidmembership.condition.ActIdMembershipWhere;
import com.zhongan.dwe.dao.repository.actidmembership.condition.ActIdMembershipCondition;

@Service("actIdMembershipBiz")
public class ActIdMembershipBizImpl implements ActIdMembershipBiz {
	
	@Autowired
	private ActIdMembershipRepository actIdMembershipRepository;
	
	@Override
	public int add(ActIdMembershipDTO actIdMembershipDTO) {
		ActIdMembership actIdMembership = new ActIdMembership();
		BeanUtils.copyProperties(actIdMembershipDTO, actIdMembership);
		int insert = actIdMembershipRepository.insert(actIdMembership);
		return insert;
	}

	@Override
	public List<ActIdMembershipDTO> findAll() {
		return convert(actIdMembershipRepository.findAll());
	}
	
	@Override
	public PageList<ActIdMembershipDTO> findAll(int start, int limit) {
		return findByCondition(start,limit,null);
	}

	@Override
	public PageList<ActIdMembershipDTO> findByCondition(int start, int limit,ActIdMembershipWhere where) {
		return findByCondition(start,limit,where,null);
	}
	
	@Override
	public PageList<ActIdMembershipDTO> findByCondition(int start, int limit,ActIdMembershipWhere where,OrderByInfo orderBy) {
		return findByCondition(start,limit,where,orderBy,null);
	}
	
	@Override
	public PageList<ActIdMembershipDTO> findByCondition(int start, int limit,ActIdMembershipWhere where,OrderByInfo orderBy,ActIdMembershipSelect select) {
		int count = actIdMembershipRepository.countByCondition(where);
		ActIdMembershipCondition condition = new ActIdMembershipCondition();
		PageInfo page = new PageInfo();
		page.setStart(start);
		page.setLimit(limit);
		condition.setPage(page);
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return new PageListImpl<ActIdMembershipDTO>(count, convert(actIdMembershipRepository.findByCondition(condition)));		
	}	
	
	@Override
	public List<ActIdMembershipDTO> findByCondition(ActIdMembershipWhere where) {
		return findByCondition(where,null);
	}
	
	@Override
	public List<ActIdMembershipDTO> findByCondition(ActIdMembershipWhere where,OrderByInfo orderBy) {
		return findByCondition(where,orderBy,null);
	}
	
	@Override
	public List<ActIdMembershipDTO> findByCondition(ActIdMembershipWhere where,OrderByInfo orderBy,ActIdMembershipSelect select) {
		ActIdMembershipCondition condition = new ActIdMembershipCondition();
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return convert(actIdMembershipRepository.findByCondition(condition));
	}
	
	private List<ActIdMembershipDTO> convert(List<ActIdMembership> actIdMemberships) {
		List<ActIdMembershipDTO> actIdMembershipDTOs = new ArrayList<ActIdMembershipDTO>();
		ActIdMembershipDTO actIdMembershipDTO;
		for (ActIdMembership actIdMembership : actIdMemberships) {
			actIdMembershipDTO = new ActIdMembershipDTO();
			BeanUtils.copyProperties(actIdMembership, actIdMembershipDTO);
			actIdMembershipDTOs.add(actIdMembershipDTO);
		}
		return actIdMembershipDTOs;
	}

}
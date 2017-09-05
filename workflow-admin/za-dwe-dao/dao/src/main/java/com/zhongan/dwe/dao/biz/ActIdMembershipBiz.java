package com.zhongan.dwe.dao.biz;

import java.io.Serializable;
import java.util.List;

import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageList;
import com.zhongan.dwe.dao.dto.ActIdMembershipDTO;
import com.zhongan.dwe.dao.repository.actidmembership.condition.ActIdMembershipSelect;
import com.zhongan.dwe.dao.repository.actidmembership.condition.ActIdMembershipWhere;

public interface ActIdMembershipBiz {
	
	public int add(ActIdMembershipDTO actIdMembershipDTO);
	
	public PageList<ActIdMembershipDTO> findAll(int start, int limit);
	
	public PageList<ActIdMembershipDTO> findByCondition(int start, int limit,ActIdMembershipWhere where);
	
	public PageList<ActIdMembershipDTO> findByCondition(int start, int limit,ActIdMembershipWhere where,OrderByInfo orderBy);
	
	public PageList<ActIdMembershipDTO> findByCondition(int start, int limit,ActIdMembershipWhere where,OrderByInfo orderBy,ActIdMembershipSelect select);

	public List<ActIdMembershipDTO> findByCondition(ActIdMembershipWhere where);
	
	public List<ActIdMembershipDTO> findByCondition(ActIdMembershipWhere where,OrderByInfo orderBy);
	
	public List<ActIdMembershipDTO> findByCondition(ActIdMembershipWhere where,OrderByInfo orderBy,ActIdMembershipSelect select);
	
	public List<ActIdMembershipDTO> findAll();
	
}

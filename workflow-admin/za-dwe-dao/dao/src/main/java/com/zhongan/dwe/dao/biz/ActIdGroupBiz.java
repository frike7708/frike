package com.zhongan.dwe.dao.biz;

import java.io.Serializable;
import java.util.List;

import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageList;
import com.zhongan.dwe.dao.dto.ActIdGroupDTO;
import com.zhongan.dwe.dao.repository.actidgroup.condition.ActIdGroupSelect;
import com.zhongan.dwe.dao.repository.actidgroup.condition.ActIdGroupWhere;

public interface ActIdGroupBiz {
	
	public int add(ActIdGroupDTO actIdGroupDTO);

	public int update(ActIdGroupDTO actIdGroupDTO, String id);
	
	public int updateByCondition(ActIdGroupDTO actIdGroupDTO, ActIdGroupWhere where);

	public int delete(String id);

	public ActIdGroupDTO getByID_(String id);

	public PageList<ActIdGroupDTO> findAll(int start, int limit);
	
	public PageList<ActIdGroupDTO> findByCondition(int start, int limit,ActIdGroupWhere where);
	
	public PageList<ActIdGroupDTO> findByCondition(int start, int limit,ActIdGroupWhere where,OrderByInfo orderBy);
	
	public PageList<ActIdGroupDTO> findByCondition(int start, int limit,ActIdGroupWhere where,OrderByInfo orderBy,ActIdGroupSelect select);

	public List<ActIdGroupDTO> findByCondition(ActIdGroupWhere where);
	
	public List<ActIdGroupDTO> findByCondition(ActIdGroupWhere where,OrderByInfo orderBy);
	
	public List<ActIdGroupDTO> findByCondition(ActIdGroupWhere where,OrderByInfo orderBy,ActIdGroupSelect select);
	
	public List<ActIdGroupDTO> findAll();
	
}

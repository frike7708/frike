package com.zhongan.dwe.dao.biz;

import java.io.Serializable;
import java.util.List;

import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageList;
import com.zhongan.dwe.dao.dto.ActIdInfoDTO;
import com.zhongan.dwe.dao.repository.actidinfo.condition.ActIdInfoSelect;
import com.zhongan.dwe.dao.repository.actidinfo.condition.ActIdInfoWhere;

public interface ActIdInfoBiz {
	
	public int add(ActIdInfoDTO actIdInfoDTO);

	public int update(ActIdInfoDTO actIdInfoDTO, String id);
	
	public int updateByCondition(ActIdInfoDTO actIdInfoDTO, ActIdInfoWhere where);

	public int delete(String id);

	public ActIdInfoDTO getByID_(String id);

	public PageList<ActIdInfoDTO> findAll(int start, int limit);
	
	public PageList<ActIdInfoDTO> findByCondition(int start, int limit,ActIdInfoWhere where);
	
	public PageList<ActIdInfoDTO> findByCondition(int start, int limit,ActIdInfoWhere where,OrderByInfo orderBy);
	
	public PageList<ActIdInfoDTO> findByCondition(int start, int limit,ActIdInfoWhere where,OrderByInfo orderBy,ActIdInfoSelect select);

	public List<ActIdInfoDTO> findByCondition(ActIdInfoWhere where);
	
	public List<ActIdInfoDTO> findByCondition(ActIdInfoWhere where,OrderByInfo orderBy);
	
	public List<ActIdInfoDTO> findByCondition(ActIdInfoWhere where,OrderByInfo orderBy,ActIdInfoSelect select);
	
	public List<ActIdInfoDTO> findAll();
	
}

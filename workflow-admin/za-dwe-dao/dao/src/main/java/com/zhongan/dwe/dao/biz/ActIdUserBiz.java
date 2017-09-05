package com.zhongan.dwe.dao.biz;

import java.io.Serializable;
import java.util.List;

import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageList;
import com.zhongan.dwe.dao.dto.ActIdUserDTO;
import com.zhongan.dwe.dao.repository.actiduser.condition.ActIdUserSelect;
import com.zhongan.dwe.dao.repository.actiduser.condition.ActIdUserWhere;

public interface ActIdUserBiz {
	
	public int add(ActIdUserDTO actIdUserDTO);

	public int update(ActIdUserDTO actIdUserDTO, String id);
	
	public int updateByCondition(ActIdUserDTO actIdUserDTO, ActIdUserWhere where);

	public int delete(String id);

	public ActIdUserDTO getByID_(String id);

	public PageList<ActIdUserDTO> findAll(int start, int limit);
	
	public PageList<ActIdUserDTO> findByCondition(int start, int limit,ActIdUserWhere where);
	
	public PageList<ActIdUserDTO> findByCondition(int start, int limit,ActIdUserWhere where,OrderByInfo orderBy);
	
	public PageList<ActIdUserDTO> findByCondition(int start, int limit,ActIdUserWhere where,OrderByInfo orderBy,ActIdUserSelect select);

	public List<ActIdUserDTO> findByCondition(ActIdUserWhere where);
	
	public List<ActIdUserDTO> findByCondition(ActIdUserWhere where,OrderByInfo orderBy);
	
	public List<ActIdUserDTO> findByCondition(ActIdUserWhere where,OrderByInfo orderBy,ActIdUserSelect select);
	
	public List<ActIdUserDTO> findAll();
	
}

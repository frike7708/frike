package com.zhongan.dwe.dao.biz;

import java.io.Serializable;
import java.util.List;

import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageList;
import com.zhongan.dwe.dao.dto.TbExtUserDTO;
import com.zhongan.dwe.dao.repository.tbextuser.condition.TbExtUserSelect;
import com.zhongan.dwe.dao.repository.tbextuser.condition.TbExtUserWhere;

public interface TbExtUserBiz {
	
	public int add(TbExtUserDTO tbExtUserDTO);

	public int update(TbExtUserDTO tbExtUserDTO, String id);
	
	public int updateByCondition(TbExtUserDTO tbExtUserDTO, TbExtUserWhere where);

	public int delete(String id);

	public TbExtUserDTO getByID(String id);

	public PageList<TbExtUserDTO> findAll(int start, int limit);
	
	public PageList<TbExtUserDTO> findByCondition(int start, int limit,TbExtUserWhere where);
	
	public PageList<TbExtUserDTO> findByCondition(int start, int limit,TbExtUserWhere where,OrderByInfo orderBy);
	
	public PageList<TbExtUserDTO> findByCondition(int start, int limit,TbExtUserWhere where,OrderByInfo orderBy,TbExtUserSelect select);

	public List<TbExtUserDTO> findByCondition(TbExtUserWhere where);
	
	public List<TbExtUserDTO> findByCondition(TbExtUserWhere where,OrderByInfo orderBy);
	
	public List<TbExtUserDTO> findByCondition(TbExtUserWhere where,OrderByInfo orderBy,TbExtUserSelect select);
	
	public List<TbExtUserDTO> findAll();
	
}

package com.zhongan.dwe.dao.biz;

import java.io.Serializable;
import java.util.List;

import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageList;
import com.zhongan.dwe.dao.dto.TbErrorLogDTO;
import com.zhongan.dwe.dao.repository.tberrorlog.condition.TbErrorLogSelect;
import com.zhongan.dwe.dao.repository.tberrorlog.condition.TbErrorLogWhere;

public interface TbErrorLogBiz {
	
	public int add(TbErrorLogDTO tbErrorLogDTO);

	public int update(TbErrorLogDTO tbErrorLogDTO, String id);
	
	public int updateByCondition(TbErrorLogDTO tbErrorLogDTO, TbErrorLogWhere where);

	public int delete(String id);

	public TbErrorLogDTO getByID(String id);

	public PageList<TbErrorLogDTO> findAll(int start, int limit);
	
	public PageList<TbErrorLogDTO> findByCondition(int start, int limit,TbErrorLogWhere where);
	
	public PageList<TbErrorLogDTO> findByCondition(int start, int limit,TbErrorLogWhere where,OrderByInfo orderBy);
	
	public PageList<TbErrorLogDTO> findByCondition(int start, int limit,TbErrorLogWhere where,OrderByInfo orderBy,TbErrorLogSelect select);

	public List<TbErrorLogDTO> findByCondition(TbErrorLogWhere where);
	
	public List<TbErrorLogDTO> findByCondition(TbErrorLogWhere where,OrderByInfo orderBy);
	
	public List<TbErrorLogDTO> findByCondition(TbErrorLogWhere where,OrderByInfo orderBy,TbErrorLogSelect select);
	
	public List<TbErrorLogDTO> findAll();
	
}

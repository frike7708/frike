package com.zhongan.dwe.dao.biz;

import java.io.Serializable;
import java.util.List;

import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageList;
import com.zhongan.dwe.dao.dto.TbServerUrlDTO;
import com.zhongan.dwe.dao.repository.tbserverurl.condition.TbServerUrlSelect;
import com.zhongan.dwe.dao.repository.tbserverurl.condition.TbServerUrlWhere;

public interface TbServerUrlBiz {
	
	public int add(TbServerUrlDTO tbServerUrlDTO);

	public int update(TbServerUrlDTO tbServerUrlDTO, String id);
	
	public int updateByCondition(TbServerUrlDTO tbServerUrlDTO, TbServerUrlWhere where);

	public int delete(String id);

	public TbServerUrlDTO getByID(String id);

	public PageList<TbServerUrlDTO> findAll(int start, int limit);
	
	public PageList<TbServerUrlDTO> findByCondition(int start, int limit,TbServerUrlWhere where);
	
	public PageList<TbServerUrlDTO> findByCondition(int start, int limit,TbServerUrlWhere where,OrderByInfo orderBy);
	
	public PageList<TbServerUrlDTO> findByCondition(int start, int limit,TbServerUrlWhere where,OrderByInfo orderBy,TbServerUrlSelect select);

	public List<TbServerUrlDTO> findByCondition(TbServerUrlWhere where);
	
	public List<TbServerUrlDTO> findByCondition(TbServerUrlWhere where,OrderByInfo orderBy);
	
	public List<TbServerUrlDTO> findByCondition(TbServerUrlWhere where,OrderByInfo orderBy,TbServerUrlSelect select);
	
	public List<TbServerUrlDTO> findAll();
	
}

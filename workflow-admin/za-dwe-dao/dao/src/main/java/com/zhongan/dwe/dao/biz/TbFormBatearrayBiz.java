package com.zhongan.dwe.dao.biz;

import java.util.List;

import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageList;
import com.zhongan.dwe.dao.dto.TbFormBatearrayDTO;
import com.zhongan.dwe.dao.repository.tbformbatearray.condition.TbFormBatearraySelect;
import com.zhongan.dwe.dao.repository.tbformbatearray.condition.TbFormBatearrayWhere;

public interface TbFormBatearrayBiz {
	
	public int add(TbFormBatearrayDTO tbFormBatearrayDTO);

	public int update(TbFormBatearrayDTO tbFormBatearrayDTO, String id);
	
	public int updateByCondition(TbFormBatearrayDTO tbFormBatearrayDTO, TbFormBatearrayWhere where);

	public int delete(String id);

	public TbFormBatearrayDTO getByID(String id);

	public PageList<TbFormBatearrayDTO> findAll(int start, int limit);
	
	public PageList<TbFormBatearrayDTO> findByCondition(int start, int limit,TbFormBatearrayWhere where);
	
	public PageList<TbFormBatearrayDTO> findByCondition(int start, int limit,TbFormBatearrayWhere where,OrderByInfo orderBy);
	
	public PageList<TbFormBatearrayDTO> findByCondition(int start, int limit,TbFormBatearrayWhere where,OrderByInfo orderBy,TbFormBatearraySelect select);

	public List<TbFormBatearrayDTO> findByCondition(TbFormBatearrayWhere where);
	
	public List<TbFormBatearrayDTO> findByCondition(TbFormBatearrayWhere where,OrderByInfo orderBy);
	
	public List<TbFormBatearrayDTO> findByCondition(TbFormBatearrayWhere where,OrderByInfo orderBy,TbFormBatearraySelect select);
	
	public List<TbFormBatearrayDTO> findAll();
	
}

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
import com.zhongan.dwe.dao.biz.TbFormBatearrayBiz;
import com.zhongan.dwe.dao.dataobject.TbFormBatearray;
import com.zhongan.dwe.dao.dto.TbFormBatearrayDTO;
import com.zhongan.dwe.dao.repository.tbformbatearray.TbFormBatearrayRepository;
import com.zhongan.dwe.dao.repository.tbformbatearray.condition.TbFormBatearraySelect;
import com.zhongan.dwe.dao.repository.tbformbatearray.condition.TbFormBatearrayWhere;
import com.zhongan.dwe.dao.repository.tbformbatearray.condition.TbFormBatearrayCondition;

@Service("tbFormBatearrayBiz")
public class TbFormBatearrayBizImpl implements TbFormBatearrayBiz {

	@Autowired
	private TbFormBatearrayRepository tbFormBatearrayRepository;

	@Override
	@Transactional
	public int add(TbFormBatearrayDTO tbFormBatearrayDTO) {
		TbFormBatearray tbFormBatearray = new TbFormBatearray();
		BeanUtils.copyProperties(tbFormBatearrayDTO, tbFormBatearray);
		int numRow = tbFormBatearrayRepository.insert(tbFormBatearray);
		tbFormBatearrayDTO.setId(tbFormBatearray.getId());
		return numRow;
	}

	@Override
	@Transactional
	public int update(TbFormBatearrayDTO tbFormBatearrayDTO, String id) {
		TbFormBatearray tbFormBatearray = tbFormBatearrayRepository.get(id);
		int numRow = 0;
		if (tbFormBatearray != null) {
			BeanUtils.copyProperties(tbFormBatearrayDTO, tbFormBatearray);
			tbFormBatearray.setId(id);
			numRow = tbFormBatearrayRepository.update(tbFormBatearray);
		}
		return numRow;
	}
	
	@Override
	@Transactional
	public int updateByCondition(TbFormBatearrayDTO tbFormBatearrayDTO, TbFormBatearrayWhere where) {
		TbFormBatearray tbFormBatearray = new TbFormBatearray();
		BeanUtils.copyProperties(tbFormBatearrayDTO, tbFormBatearray);
		return tbFormBatearrayRepository.updateByCondition(tbFormBatearray,where);
	}	

	@Override
	@Transactional
	public int delete(String id) {
		return tbFormBatearrayRepository.deleteByOid(id);
	}

	@Override
	public TbFormBatearrayDTO getByID(String id) {
		TbFormBatearrayDTO tbFormBatearrayDTO = new TbFormBatearrayDTO();
		TbFormBatearray tbFormBatearray = tbFormBatearrayRepository.get(id);
		if (tbFormBatearray == null) {
			return null;
		}
		BeanUtils.copyProperties(tbFormBatearray, tbFormBatearrayDTO);
		return tbFormBatearrayDTO;
	}


	@Override
	public List<TbFormBatearrayDTO> findAll() {
		return convert(tbFormBatearrayRepository.findAll());
	}
	
	@Override
	public PageList<TbFormBatearrayDTO> findAll(int start, int limit) {
		return findByCondition(start,limit,null);
	}

	@Override
	public PageList<TbFormBatearrayDTO> findByCondition(int start, int limit,TbFormBatearrayWhere where) {
		return findByCondition(start,limit,where,null);
	}
	
	@Override
	public PageList<TbFormBatearrayDTO> findByCondition(int start, int limit,TbFormBatearrayWhere where,OrderByInfo orderBy) {
		return findByCondition(start,limit,where,orderBy,null);
	}
	
	@Override
	public PageList<TbFormBatearrayDTO> findByCondition(int start, int limit,TbFormBatearrayWhere where,OrderByInfo orderBy,TbFormBatearraySelect select) {
		int count = tbFormBatearrayRepository.countByCondition(where);
		TbFormBatearrayCondition condition = new TbFormBatearrayCondition();
		PageInfo page = new PageInfo();
		page.setStart(start);
		page.setLimit(limit);
		condition.setPage(page);
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return new PageListImpl<TbFormBatearrayDTO>(count, convert(tbFormBatearrayRepository.findByCondition(condition)));		
	}	
	
	@Override
	public List<TbFormBatearrayDTO> findByCondition(TbFormBatearrayWhere where) {
		return findByCondition(where,null);
	}
	
	@Override
	public List<TbFormBatearrayDTO> findByCondition(TbFormBatearrayWhere where,OrderByInfo orderBy) {
		return findByCondition(where,orderBy,null);
	}
	
	@Override
	public List<TbFormBatearrayDTO> findByCondition(TbFormBatearrayWhere where,OrderByInfo orderBy,TbFormBatearraySelect select) {
		TbFormBatearrayCondition condition = new TbFormBatearrayCondition();
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return convert(tbFormBatearrayRepository.findByCondition(condition));
	}
	
	private List<TbFormBatearrayDTO> convert(List<TbFormBatearray> tbFormBatearrays) {
		List<TbFormBatearrayDTO> tbFormBatearrayDTOs = new ArrayList<TbFormBatearrayDTO>();
		TbFormBatearrayDTO tbFormBatearrayDTO;
		for (TbFormBatearray tbFormBatearray : tbFormBatearrays) {
			tbFormBatearrayDTO = new TbFormBatearrayDTO();
			BeanUtils.copyProperties(tbFormBatearray, tbFormBatearrayDTO);
			tbFormBatearrayDTOs.add(tbFormBatearrayDTO);
		}
		return tbFormBatearrayDTOs;
	}
}
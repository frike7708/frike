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
import com.zhongan.dwe.dao.biz.TbServerUrlBiz;
import com.zhongan.dwe.dao.dataobject.TbServerUrl;
import com.zhongan.dwe.dao.dto.TbServerUrlDTO;
import com.zhongan.dwe.dao.repository.tbserverurl.TbServerUrlRepository;
import com.zhongan.dwe.dao.repository.tbserverurl.condition.TbServerUrlSelect;
import com.zhongan.dwe.dao.repository.tbserverurl.condition.TbServerUrlWhere;
import com.zhongan.dwe.dao.repository.tbserverurl.condition.TbServerUrlCondition;

@Service("tbServerUrlBiz")
public class TbServerUrlBizImpl implements TbServerUrlBiz {

	@Autowired
	private TbServerUrlRepository tbServerUrlRepository;

	@Override
	@Transactional
	public int add(TbServerUrlDTO tbServerUrlDTO) {
		TbServerUrl tbServerUrl = new TbServerUrl();
		BeanUtils.copyProperties(tbServerUrlDTO, tbServerUrl);
		int numRow = tbServerUrlRepository.insert(tbServerUrl);
		tbServerUrlDTO.setId(tbServerUrl.getId());
		return numRow;
	}

	@Override
	@Transactional
	public int update(TbServerUrlDTO tbServerUrlDTO, String id) {
		TbServerUrl tbServerUrl = tbServerUrlRepository.get(id);
		int numRow = 0;
		if (tbServerUrl != null) {
			BeanUtils.copyProperties(tbServerUrlDTO, tbServerUrl);
			tbServerUrl.setId(id);
			numRow = tbServerUrlRepository.update(tbServerUrl);
		}
		return numRow;
	}
	
	@Override
	@Transactional
	public int updateByCondition(TbServerUrlDTO tbServerUrlDTO, TbServerUrlWhere where) {
		TbServerUrl tbServerUrl = new TbServerUrl();
		BeanUtils.copyProperties(tbServerUrlDTO, tbServerUrl);
		return tbServerUrlRepository.updateByCondition(tbServerUrl,where);
	}	

	@Override
	@Transactional
	public int delete(String id) {
		return tbServerUrlRepository.deleteByOid(id);
	}

	@Override
	public TbServerUrlDTO getByID(String id) {
		TbServerUrlDTO tbServerUrlDTO = new TbServerUrlDTO();
		TbServerUrl tbServerUrl = tbServerUrlRepository.get(id);
		if (tbServerUrl == null) {
			return null;
		}
		BeanUtils.copyProperties(tbServerUrl, tbServerUrlDTO);
		return tbServerUrlDTO;
	}


	@Override
	public List<TbServerUrlDTO> findAll() {
		return convert(tbServerUrlRepository.findAll());
	}
	
	@Override
	public PageList<TbServerUrlDTO> findAll(int start, int limit) {
		return findByCondition(start,limit,null);
	}

	@Override
	public PageList<TbServerUrlDTO> findByCondition(int start, int limit,TbServerUrlWhere where) {
		return findByCondition(start,limit,where,null);
	}
	
	@Override
	public PageList<TbServerUrlDTO> findByCondition(int start, int limit,TbServerUrlWhere where,OrderByInfo orderBy) {
		return findByCondition(start,limit,where,orderBy,null);
	}
	
	@Override
	public PageList<TbServerUrlDTO> findByCondition(int start, int limit,TbServerUrlWhere where,OrderByInfo orderBy,TbServerUrlSelect select) {
		int count = tbServerUrlRepository.countByCondition(where);
		TbServerUrlCondition condition = new TbServerUrlCondition();
		PageInfo page = new PageInfo();
		page.setStart(start);
		page.setLimit(limit);
		condition.setPage(page);
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return new PageListImpl<TbServerUrlDTO>(count, convert(tbServerUrlRepository.findByCondition(condition)));		
	}	
	
	@Override
	public List<TbServerUrlDTO> findByCondition(TbServerUrlWhere where) {
		return findByCondition(where,null);
	}
	
	@Override
	public List<TbServerUrlDTO> findByCondition(TbServerUrlWhere where,OrderByInfo orderBy) {
		return findByCondition(where,orderBy,null);
	}
	
	@Override
	public List<TbServerUrlDTO> findByCondition(TbServerUrlWhere where,OrderByInfo orderBy,TbServerUrlSelect select) {
		TbServerUrlCondition condition = new TbServerUrlCondition();
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return convert(tbServerUrlRepository.findByCondition(condition));
	}
	
	private List<TbServerUrlDTO> convert(List<TbServerUrl> tbServerUrls) {
		List<TbServerUrlDTO> tbServerUrlDTOs = new ArrayList<TbServerUrlDTO>();
		TbServerUrlDTO tbServerUrlDTO;
		for (TbServerUrl tbServerUrl : tbServerUrls) {
			tbServerUrlDTO = new TbServerUrlDTO();
			BeanUtils.copyProperties(tbServerUrl, tbServerUrlDTO);
			tbServerUrlDTOs.add(tbServerUrlDTO);
		}
		return tbServerUrlDTOs;
	}
}
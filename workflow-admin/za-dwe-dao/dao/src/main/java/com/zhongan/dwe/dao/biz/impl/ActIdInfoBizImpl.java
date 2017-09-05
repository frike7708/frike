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
import com.zhongan.dwe.dao.biz.ActIdInfoBiz;
import com.zhongan.dwe.dao.dataobject.ActIdInfo;
import com.zhongan.dwe.dao.dto.ActIdInfoDTO;
import com.zhongan.dwe.dao.repository.actidinfo.ActIdInfoRepository;
import com.zhongan.dwe.dao.repository.actidinfo.condition.ActIdInfoSelect;
import com.zhongan.dwe.dao.repository.actidinfo.condition.ActIdInfoWhere;
import com.zhongan.dwe.dao.repository.actidinfo.condition.ActIdInfoCondition;

@Service("actIdInfoBiz")
public class ActIdInfoBizImpl implements ActIdInfoBiz {

	@Autowired
	private ActIdInfoRepository actIdInfoRepository;

	@Override
	@Transactional
	public int add(ActIdInfoDTO actIdInfoDTO) {
		ActIdInfo actIdInfo = new ActIdInfo();
		BeanUtils.copyProperties(actIdInfoDTO, actIdInfo);
		int numRow = actIdInfoRepository.insert(actIdInfo);
		actIdInfoDTO.setId(actIdInfo.getId());
		return numRow;
	}

	@Override
	@Transactional
	public int update(ActIdInfoDTO actIdInfoDTO, String id) {
		ActIdInfo actIdInfo = actIdInfoRepository.get(id);
		int numRow = 0;
		if (actIdInfo != null) {
			BeanUtils.copyProperties(actIdInfoDTO, actIdInfo);
			actIdInfo.setId(id);
			numRow = actIdInfoRepository.update(actIdInfo);
		}
		return numRow;
	}
	
	@Override
	@Transactional
	public int updateByCondition(ActIdInfoDTO actIdInfoDTO, ActIdInfoWhere where) {
		ActIdInfo actIdInfo = new ActIdInfo();
		BeanUtils.copyProperties(actIdInfoDTO, actIdInfo);
		return actIdInfoRepository.updateByCondition(actIdInfo,where);
	}	

	@Override
	@Transactional
	public int delete(String id) {
		return actIdInfoRepository.deleteByOid(id);
	}

	@Override
	public ActIdInfoDTO getByID_(String id) {
		ActIdInfoDTO actIdInfoDTO = new ActIdInfoDTO();
		ActIdInfo actIdInfo = actIdInfoRepository.get(id);
		if (actIdInfo == null) {
			return null;
		}
		BeanUtils.copyProperties(actIdInfo, actIdInfoDTO);
		return actIdInfoDTO;
	}


	@Override
	public List<ActIdInfoDTO> findAll() {
		return convert(actIdInfoRepository.findAll());
	}
	
	@Override
	public PageList<ActIdInfoDTO> findAll(int start, int limit) {
		return findByCondition(start,limit,null);
	}

	@Override
	public PageList<ActIdInfoDTO> findByCondition(int start, int limit,ActIdInfoWhere where) {
		return findByCondition(start,limit,where,null);
	}
	
	@Override
	public PageList<ActIdInfoDTO> findByCondition(int start, int limit,ActIdInfoWhere where,OrderByInfo orderBy) {
		return findByCondition(start,limit,where,orderBy,null);
	}
	
	@Override
	public PageList<ActIdInfoDTO> findByCondition(int start, int limit,ActIdInfoWhere where,OrderByInfo orderBy,ActIdInfoSelect select) {
		int count = actIdInfoRepository.countByCondition(where);
		ActIdInfoCondition condition = new ActIdInfoCondition();
		PageInfo page = new PageInfo();
		page.setStart(start);
		page.setLimit(limit);
		condition.setPage(page);
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return new PageListImpl<ActIdInfoDTO>(count, convert(actIdInfoRepository.findByCondition(condition)));		
	}	
	
	@Override
	public List<ActIdInfoDTO> findByCondition(ActIdInfoWhere where) {
		return findByCondition(where,null);
	}
	
	@Override
	public List<ActIdInfoDTO> findByCondition(ActIdInfoWhere where,OrderByInfo orderBy) {
		return findByCondition(where,orderBy,null);
	}
	
	@Override
	public List<ActIdInfoDTO> findByCondition(ActIdInfoWhere where,OrderByInfo orderBy,ActIdInfoSelect select) {
		ActIdInfoCondition condition = new ActIdInfoCondition();
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return convert(actIdInfoRepository.findByCondition(condition));
	}
	
	private List<ActIdInfoDTO> convert(List<ActIdInfo> actIdInfos) {
		List<ActIdInfoDTO> actIdInfoDTOs = new ArrayList<ActIdInfoDTO>();
		ActIdInfoDTO actIdInfoDTO;
		for (ActIdInfo actIdInfo : actIdInfos) {
			actIdInfoDTO = new ActIdInfoDTO();
			BeanUtils.copyProperties(actIdInfo, actIdInfoDTO);
			actIdInfoDTOs.add(actIdInfoDTO);
		}
		return actIdInfoDTOs;
	}
}
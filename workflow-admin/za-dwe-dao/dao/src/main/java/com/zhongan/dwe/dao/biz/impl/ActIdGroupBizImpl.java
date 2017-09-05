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
import com.zhongan.dwe.dao.biz.ActIdGroupBiz;
import com.zhongan.dwe.dao.dataobject.ActIdGroup;
import com.zhongan.dwe.dao.dto.ActIdGroupDTO;
import com.zhongan.dwe.dao.repository.actidgroup.ActIdGroupRepository;
import com.zhongan.dwe.dao.repository.actidgroup.condition.ActIdGroupSelect;
import com.zhongan.dwe.dao.repository.actidgroup.condition.ActIdGroupWhere;
import com.zhongan.dwe.dao.repository.actidgroup.condition.ActIdGroupCondition;

@Service("actIdGroupBiz")
public class ActIdGroupBizImpl implements ActIdGroupBiz {

	@Autowired
	private ActIdGroupRepository actIdGroupRepository;

	@Override
	@Transactional
	public int add(ActIdGroupDTO actIdGroupDTO) {
		ActIdGroup actIdGroup = new ActIdGroup();
		BeanUtils.copyProperties(actIdGroupDTO, actIdGroup);
		int numRow = actIdGroupRepository.insert(actIdGroup);
		actIdGroupDTO.setId(actIdGroup.getId());
		return numRow;
	}

	@Override
	@Transactional
	public int update(ActIdGroupDTO actIdGroupDTO, String id) {
		ActIdGroup actIdGroup = actIdGroupRepository.get(id);
		int numRow = 0;
		if (actIdGroup != null) {
			BeanUtils.copyProperties(actIdGroupDTO, actIdGroup);
			actIdGroup.setId(id);
			numRow = actIdGroupRepository.update(actIdGroup);
		}
		return numRow;
	}
	
	@Override
	@Transactional
	public int updateByCondition(ActIdGroupDTO actIdGroupDTO, ActIdGroupWhere where) {
		ActIdGroup actIdGroup = new ActIdGroup();
		BeanUtils.copyProperties(actIdGroupDTO, actIdGroup);
		return actIdGroupRepository.updateByCondition(actIdGroup,where);
	}	

	@Override
	@Transactional
	public int delete(String id) {
		return actIdGroupRepository.deleteByOid(id);
	}

	@Override
	public ActIdGroupDTO getByID_(String id) {
		ActIdGroupDTO actIdGroupDTO = new ActIdGroupDTO();
		ActIdGroup actIdGroup = actIdGroupRepository.get(id);
		if (actIdGroup == null) {
			return null;
		}
		BeanUtils.copyProperties(actIdGroup, actIdGroupDTO);
		return actIdGroupDTO;
	}


	@Override
	public List<ActIdGroupDTO> findAll() {
		return convert(actIdGroupRepository.findAll());
	}
	
	@Override
	public PageList<ActIdGroupDTO> findAll(int start, int limit) {
		return findByCondition(start,limit,null);
	}

	@Override
	public PageList<ActIdGroupDTO> findByCondition(int start, int limit,ActIdGroupWhere where) {
		return findByCondition(start,limit,where,null);
	}
	
	@Override
	public PageList<ActIdGroupDTO> findByCondition(int start, int limit,ActIdGroupWhere where,OrderByInfo orderBy) {
		return findByCondition(start,limit,where,orderBy,null);
	}
	
	@Override
	public PageList<ActIdGroupDTO> findByCondition(int start, int limit,ActIdGroupWhere where,OrderByInfo orderBy,ActIdGroupSelect select) {
		int count = actIdGroupRepository.countByCondition(where);
		ActIdGroupCondition condition = new ActIdGroupCondition();
		PageInfo page = new PageInfo();
		page.setStart(start);
		page.setLimit(limit);
		condition.setPage(page);
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return new PageListImpl<ActIdGroupDTO>(count, convert(actIdGroupRepository.findByCondition(condition)));		
	}	
	
	@Override
	public List<ActIdGroupDTO> findByCondition(ActIdGroupWhere where) {
		return findByCondition(where,null);
	}
	
	@Override
	public List<ActIdGroupDTO> findByCondition(ActIdGroupWhere where,OrderByInfo orderBy) {
		return findByCondition(where,orderBy,null);
	}
	
	@Override
	public List<ActIdGroupDTO> findByCondition(ActIdGroupWhere where,OrderByInfo orderBy,ActIdGroupSelect select) {
		ActIdGroupCondition condition = new ActIdGroupCondition();
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return convert(actIdGroupRepository.findByCondition(condition));
	}
	
	private List<ActIdGroupDTO> convert(List<ActIdGroup> actIdGroups) {
		List<ActIdGroupDTO> actIdGroupDTOs = new ArrayList<ActIdGroupDTO>();
		ActIdGroupDTO actIdGroupDTO;
		for (ActIdGroup actIdGroup : actIdGroups) {
			actIdGroupDTO = new ActIdGroupDTO();
			BeanUtils.copyProperties(actIdGroup, actIdGroupDTO);
			actIdGroupDTOs.add(actIdGroupDTO);
		}
		return actIdGroupDTOs;
	}
}
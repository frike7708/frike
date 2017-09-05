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
import com.zhongan.dwe.dao.biz.ActIdUserBiz;
import com.zhongan.dwe.dao.dataobject.ActIdUser;
import com.zhongan.dwe.dao.dto.ActIdUserDTO;
import com.zhongan.dwe.dao.repository.actiduser.ActIdUserRepository;
import com.zhongan.dwe.dao.repository.actiduser.condition.ActIdUserSelect;
import com.zhongan.dwe.dao.repository.actiduser.condition.ActIdUserWhere;
import com.zhongan.dwe.dao.repository.actiduser.condition.ActIdUserCondition;

@Service("actIdUserBiz")
public class ActIdUserBizImpl implements ActIdUserBiz {

	@Autowired
	private ActIdUserRepository actIdUserRepository;

	@Override
	@Transactional
	public int add(ActIdUserDTO actIdUserDTO) {
		ActIdUser actIdUser = new ActIdUser();
		BeanUtils.copyProperties(actIdUserDTO, actIdUser);
		int numRow = actIdUserRepository.insert(actIdUser);
		actIdUserDTO.setId(actIdUser.getId());
		return numRow;
	}

	@Override
	@Transactional
	public int update(ActIdUserDTO actIdUserDTO, String id) {
		ActIdUser actIdUser = actIdUserRepository.get(id);
		int numRow = 0;
		if (actIdUser != null) {
			BeanUtils.copyProperties(actIdUserDTO, actIdUser);
			actIdUser.setId(id);
			numRow = actIdUserRepository.update(actIdUser);
		}
		return numRow;
	}
	
	@Override
	@Transactional
	public int updateByCondition(ActIdUserDTO actIdUserDTO, ActIdUserWhere where) {
		ActIdUser actIdUser = new ActIdUser();
		BeanUtils.copyProperties(actIdUserDTO, actIdUser);
		return actIdUserRepository.updateByCondition(actIdUser,where);
	}	

	@Override
	@Transactional
	public int delete(String id) {
		return actIdUserRepository.deleteByOid(id);
	}

	@Override
	public ActIdUserDTO getByID_(String id) {
		ActIdUserDTO actIdUserDTO = new ActIdUserDTO();
		ActIdUser actIdUser = actIdUserRepository.get(id);
		if (actIdUser == null) {
			return null;
		}
		BeanUtils.copyProperties(actIdUser, actIdUserDTO);
		return actIdUserDTO;
	}


	@Override
	public List<ActIdUserDTO> findAll() {
		return convert(actIdUserRepository.findAll());
	}
	
	@Override
	public PageList<ActIdUserDTO> findAll(int start, int limit) {
		return findByCondition(start,limit,null);
	}

	@Override
	public PageList<ActIdUserDTO> findByCondition(int start, int limit,ActIdUserWhere where) {
		return findByCondition(start,limit,where,null);
	}
	
	@Override
	public PageList<ActIdUserDTO> findByCondition(int start, int limit,ActIdUserWhere where,OrderByInfo orderBy) {
		return findByCondition(start,limit,where,orderBy,null);
	}
	
	@Override
	public PageList<ActIdUserDTO> findByCondition(int start, int limit,ActIdUserWhere where,OrderByInfo orderBy,ActIdUserSelect select) {
		int count = actIdUserRepository.countByCondition(where);
		ActIdUserCondition condition = new ActIdUserCondition();
		PageInfo page = new PageInfo();
		page.setStart(start);
		page.setLimit(limit);
		condition.setPage(page);
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return new PageListImpl<ActIdUserDTO>(count, convert(actIdUserRepository.findByCondition(condition)));		
	}	
	
	@Override
	public List<ActIdUserDTO> findByCondition(ActIdUserWhere where) {
		return findByCondition(where,null);
	}
	
	@Override
	public List<ActIdUserDTO> findByCondition(ActIdUserWhere where,OrderByInfo orderBy) {
		return findByCondition(where,orderBy,null);
	}
	
	@Override
	public List<ActIdUserDTO> findByCondition(ActIdUserWhere where,OrderByInfo orderBy,ActIdUserSelect select) {
		ActIdUserCondition condition = new ActIdUserCondition();
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return convert(actIdUserRepository.findByCondition(condition));
	}
	
	private List<ActIdUserDTO> convert(List<ActIdUser> actIdUsers) {
		List<ActIdUserDTO> actIdUserDTOs = new ArrayList<ActIdUserDTO>();
		ActIdUserDTO actIdUserDTO;
		for (ActIdUser actIdUser : actIdUsers) {
			actIdUserDTO = new ActIdUserDTO();
			BeanUtils.copyProperties(actIdUser, actIdUserDTO);
			actIdUserDTOs.add(actIdUserDTO);
		}
		return actIdUserDTOs;
	}
}
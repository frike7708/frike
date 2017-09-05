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
import com.zhongan.dwe.dao.biz.TbExtUserBiz;
import com.zhongan.dwe.dao.dataobject.TbExtUser;
import com.zhongan.dwe.dao.dto.TbExtUserDTO;
import com.zhongan.dwe.dao.repository.tbextuser.TbExtUserRepository;
import com.zhongan.dwe.dao.repository.tbextuser.condition.TbExtUserSelect;
import com.zhongan.dwe.dao.repository.tbextuser.condition.TbExtUserWhere;
import com.zhongan.dwe.dao.repository.tbextuser.condition.TbExtUserCondition;

@Service("tbExtUserBiz")
public class TbExtUserBizImpl implements TbExtUserBiz {

	@Autowired
	private TbExtUserRepository tbExtUserRepository;

	@Override
	@Transactional
	public int add(TbExtUserDTO tbExtUserDTO) {
		TbExtUser tbExtUser = new TbExtUser();
		BeanUtils.copyProperties(tbExtUserDTO, tbExtUser);
		int numRow = tbExtUserRepository.insert(tbExtUser);
		tbExtUserDTO.setId(tbExtUser.getId());
		return numRow;
	}

	@Override
	@Transactional
	public int update(TbExtUserDTO tbExtUserDTO, String id) {
		TbExtUser tbExtUser = tbExtUserRepository.get(id);
		int numRow = 0;
		if (tbExtUser != null) {
			BeanUtils.copyProperties(tbExtUserDTO, tbExtUser);
			tbExtUser.setId(id);
			numRow = tbExtUserRepository.update(tbExtUser);
		}
		return numRow;
	}
	
	@Override
	@Transactional
	public int updateByCondition(TbExtUserDTO tbExtUserDTO, TbExtUserWhere where) {
		TbExtUser tbExtUser = new TbExtUser();
		BeanUtils.copyProperties(tbExtUserDTO, tbExtUser);
		return tbExtUserRepository.updateByCondition(tbExtUser,where);
	}	

	@Override
	@Transactional
	public int delete(String id) {
		return tbExtUserRepository.deleteByOid(id);
	}

	@Override
	public TbExtUserDTO getByID(String id) {
		TbExtUserDTO tbExtUserDTO = new TbExtUserDTO();
		TbExtUser tbExtUser = tbExtUserRepository.get(id);
		if (tbExtUser == null) {
			return null;
		}
		BeanUtils.copyProperties(tbExtUser, tbExtUserDTO);
		return tbExtUserDTO;
	}


	@Override
	public List<TbExtUserDTO> findAll() {
		return convert(tbExtUserRepository.findAll());
	}
	
	@Override
	public PageList<TbExtUserDTO> findAll(int start, int limit) {
		return findByCondition(start,limit,null);
	}

	@Override
	public PageList<TbExtUserDTO> findByCondition(int start, int limit,TbExtUserWhere where) {
		return findByCondition(start,limit,where,null);
	}
	
	@Override
	public PageList<TbExtUserDTO> findByCondition(int start, int limit,TbExtUserWhere where,OrderByInfo orderBy) {
		return findByCondition(start,limit,where,orderBy,null);
	}
	
	@Override
	public PageList<TbExtUserDTO> findByCondition(int start, int limit,TbExtUserWhere where,OrderByInfo orderBy,TbExtUserSelect select) {
		int count = tbExtUserRepository.countByCondition(where);
		TbExtUserCondition condition = new TbExtUserCondition();
		PageInfo page = new PageInfo();
		page.setStart(start);
		page.setLimit(limit);
		condition.setPage(page);
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return new PageListImpl<TbExtUserDTO>(count, convert(tbExtUserRepository.findByCondition(condition)));		
	}	
	
	@Override
	public List<TbExtUserDTO> findByCondition(TbExtUserWhere where) {
		return findByCondition(where,null);
	}
	
	@Override
	public List<TbExtUserDTO> findByCondition(TbExtUserWhere where,OrderByInfo orderBy) {
		return findByCondition(where,orderBy,null);
	}
	
	@Override
	public List<TbExtUserDTO> findByCondition(TbExtUserWhere where,OrderByInfo orderBy,TbExtUserSelect select) {
		TbExtUserCondition condition = new TbExtUserCondition();
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return convert(tbExtUserRepository.findByCondition(condition));
	}
	
	private List<TbExtUserDTO> convert(List<TbExtUser> tbExtUsers) {
		List<TbExtUserDTO> tbExtUserDTOs = new ArrayList<TbExtUserDTO>();
		TbExtUserDTO tbExtUserDTO;
		for (TbExtUser tbExtUser : tbExtUsers) {
			tbExtUserDTO = new TbExtUserDTO();
			BeanUtils.copyProperties(tbExtUser, tbExtUserDTO);
			tbExtUserDTOs.add(tbExtUserDTO);
		}
		return tbExtUserDTOs;
	}
}
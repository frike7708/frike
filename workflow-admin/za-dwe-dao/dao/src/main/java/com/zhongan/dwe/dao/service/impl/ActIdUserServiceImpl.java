package com.zhongan.dwe.dao.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zhongan.base.repository.PageList;
import com.zhongan.dsf.provider.annotation.Provider;
import com.zhongan.dwe.dao.biz.ActIdUserBiz;
import com.zhongan.dwe.dao.dto.ActIdUserDTO;
import com.zhongan.dwe.dao.service.ActIdUserService;


@Provider
@Service("actIdUserService")
public class ActIdUserServiceImpl implements ActIdUserService {

	@Autowired
	@Qualifier("actIdUserBiz")
	private ActIdUserBiz actIdUserBiz;

	@Override
	public void add(ActIdUserDTO actIdUserDTO) {
		actIdUserBiz.add(actIdUserDTO);
	}

	@Override 
	public void update(ActIdUserDTO actIdUserDTO, String id) {
		actIdUserBiz.update(actIdUserDTO, id);
	}

	@Override
	public void delete(String id) {
		actIdUserBiz.delete(id);
	}

	@Override
	public ActIdUserDTO getByID_(String id) {
		return actIdUserBiz.getByID_(id);
	}

	@Override
	public List<ActIdUserDTO> selectUserList() {
		return actIdUserBiz.findAll();
	}

	@Override
	public List<ActIdUserDTO> selectUserList(int start, int limit) {
		PageList<ActIdUserDTO> findAll = actIdUserBiz.findAll(start, limit);
		return findAll.getRecords();
	}

}
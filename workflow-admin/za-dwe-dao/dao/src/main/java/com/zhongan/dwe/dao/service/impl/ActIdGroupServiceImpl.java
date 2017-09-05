package com.zhongan.dwe.dao.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zhongan.base.repository.PageList;
import com.zhongan.dsf.provider.annotation.Provider;
import com.zhongan.dwe.dao.biz.ActIdGroupBiz;
import com.zhongan.dwe.dao.dto.ActIdGroupDTO;
import com.zhongan.dwe.dao.service.ActIdGroupService;


@Provider
@Service("actIdGroupService")
public class ActIdGroupServiceImpl implements ActIdGroupService {

	@Autowired
	@Qualifier("actIdGroupBiz")
	private ActIdGroupBiz actIdGroupBiz;

	@Override
	public void add(ActIdGroupDTO actIdGroupDTO) {
		actIdGroupBiz.add(actIdGroupDTO);
	}

	@Override 
	public void update(ActIdGroupDTO actIdGroupDTO, String id) {
		actIdGroupBiz.update(actIdGroupDTO, id);
	}

	@Override
	public void delete(String id) {
		actIdGroupBiz.delete(id);
	}

	@Override
	public ActIdGroupDTO getByID_(String id) {
		return actIdGroupBiz.getByID_(id);
	}

	@Override
	public List<ActIdGroupDTO> selectGroupList() {
		return actIdGroupBiz.findAll();
	}

	@Override
	public List<ActIdGroupDTO> selectGroupList(int start, int limit) {
		PageList<ActIdGroupDTO> findAll = actIdGroupBiz.findAll(start, limit);
		return findAll.getRecords();
	}

}
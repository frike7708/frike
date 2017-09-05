package com.zhongan.dwe.dao.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zhongan.dsf.provider.annotation.Provider;
import com.zhongan.dwe.dao.biz.ActIdInfoBiz;
import com.zhongan.dwe.dao.dto.ActIdInfoDTO;
import com.zhongan.dwe.dao.service.ActIdInfoService;


@Provider
@Service("actIdInfoService")
public class ActIdInfoServiceImpl implements ActIdInfoService {

	@Autowired
	@Qualifier("actIdInfoBiz")
	private ActIdInfoBiz actIdInfoBiz;

	@Override
	public void add(ActIdInfoDTO actIdInfoDTO) {
		actIdInfoBiz.add(actIdInfoDTO);
	}

	@Override 
	public void update(ActIdInfoDTO actIdInfoDTO, String id) {
		actIdInfoBiz.update(actIdInfoDTO, id);
	}

	@Override
	public void delete(String id) {
		actIdInfoBiz.delete(id);
	}

	@Override
	public ActIdInfoDTO getByID_(String id) {
		return actIdInfoBiz.getByID_(id);
	}

}
package com.zhongan.dwe.dao.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zhongan.dsf.provider.annotation.Provider;
import com.zhongan.dwe.dao.biz.TbExtUserBiz;
import com.zhongan.dwe.dao.dto.TbExtUserDTO;
import com.zhongan.dwe.dao.service.TbExtUserService;


@Provider
@Service("tbExtUserService")
public class TbExtUserServiceImpl implements TbExtUserService {

	@Autowired
	@Qualifier("tbExtUserBiz")
	private TbExtUserBiz tbExtUserBiz;

	@Override
	public void add(TbExtUserDTO tbExtUserDTO) {
		tbExtUserBiz.add(tbExtUserDTO);
	}

	@Override 
	public void update(TbExtUserDTO tbExtUserDTO, String id) {
		tbExtUserBiz.update(tbExtUserDTO, id);
	}

	@Override
	public void delete(String id) {
		tbExtUserBiz.delete(id);
	}

	@Override
	public TbExtUserDTO getByID(String id) {
		return tbExtUserBiz.getByID(id);
	}

}
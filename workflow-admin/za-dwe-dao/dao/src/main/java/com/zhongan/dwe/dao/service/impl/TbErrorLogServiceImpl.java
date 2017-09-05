package com.zhongan.dwe.dao.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zhongan.dsf.provider.annotation.Provider;
import com.zhongan.dwe.dao.biz.TbErrorLogBiz;
import com.zhongan.dwe.dao.dto.TbErrorLogDTO;
import com.zhongan.dwe.dao.service.TbErrorLogService;


@Provider
@Service("tbErrorLogService")
public class TbErrorLogServiceImpl implements TbErrorLogService {

	@Autowired
	@Qualifier("tbErrorLogBiz")
	private TbErrorLogBiz tbErrorLogBiz;

	@Override
	public void add(TbErrorLogDTO tbErrorLogDTO) {
		tbErrorLogBiz.add(tbErrorLogDTO);
	}

	@Override 
	public void update(TbErrorLogDTO tbErrorLogDTO, String id) {
		tbErrorLogBiz.update(tbErrorLogDTO, id);
	}

	@Override
	public void delete(String id) {
		tbErrorLogBiz.delete(id);
	}

	@Override
	public TbErrorLogDTO getByID(String id) {
		return tbErrorLogBiz.getByID(id);
	}

}
package com.zhongan.dwe.dao.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zhongan.dsf.provider.annotation.Provider;
import com.zhongan.dwe.dao.biz.TbServerUrlBiz;
import com.zhongan.dwe.dao.dto.TbServerUrlDTO;
import com.zhongan.dwe.dao.service.TbServerUrlService;


@Provider
@Service("tbServerUrlService")
public class TbServerUrlServiceImpl implements TbServerUrlService {

	@Autowired
	@Qualifier("tbServerUrlBiz")
	private TbServerUrlBiz tbServerUrlBiz;

	@Override
	public void add(TbServerUrlDTO tbServerUrlDTO) {
		tbServerUrlBiz.add(tbServerUrlDTO);
	}

	@Override 
	public void update(TbServerUrlDTO tbServerUrlDTO, String id) {
		tbServerUrlBiz.update(tbServerUrlDTO, id);
	}

	@Override
	public void delete(String id) {
		tbServerUrlBiz.delete(id);
	}

	@Override
	public TbServerUrlDTO getByID(String id) {
		return tbServerUrlBiz.getByID(id);
	}

}
package com.zhongan.dwe.dao.service;


import com.zhongan.dwe.dao.dto.TbErrorLogDTO;

public interface TbErrorLogService {

	public void add(TbErrorLogDTO tberrorlogDTO);

	public void update(TbErrorLogDTO tberrorlogDTO,String id);

	public void delete(String id);

	public TbErrorLogDTO getByID(String id);

}

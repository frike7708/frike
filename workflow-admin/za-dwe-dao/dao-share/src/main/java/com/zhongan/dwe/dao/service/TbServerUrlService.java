package com.zhongan.dwe.dao.service;


import com.zhongan.dwe.dao.dto.TbServerUrlDTO;

public interface TbServerUrlService {

	public void add(TbServerUrlDTO tbserverurlDTO);

	public void update(TbServerUrlDTO tbserverurlDTO,String id);

	public void delete(String id);

	public TbServerUrlDTO getByID(String id);

}

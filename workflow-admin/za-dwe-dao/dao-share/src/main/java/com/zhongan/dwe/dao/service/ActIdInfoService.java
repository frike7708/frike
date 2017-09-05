package com.zhongan.dwe.dao.service;


import com.zhongan.dwe.dao.dto.ActIdInfoDTO;

public interface ActIdInfoService {

	public void add(ActIdInfoDTO actidinfoDTO);

	public void update(ActIdInfoDTO actidinfoDTO,String id);

	public void delete(String id);

	public ActIdInfoDTO getByID_(String id);

}

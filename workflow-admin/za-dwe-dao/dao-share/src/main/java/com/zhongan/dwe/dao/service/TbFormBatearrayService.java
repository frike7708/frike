package com.zhongan.dwe.dao.service;


import java.util.List;

import com.zhongan.dwe.dao.dto.TbFormBatearrayDTO;

public interface TbFormBatearrayService {

	public void add(TbFormBatearrayDTO tbformbatearrayDTO);

	public void update(TbFormBatearrayDTO tbformbatearrayDTO,String id);

	public void delete(String id);

	public TbFormBatearrayDTO getByID(String id);
	
	public List<TbFormBatearrayDTO> selectFormList();
	
	public List<TbFormBatearrayDTO> selectFormList(int start, int limit);
	
	public TbFormBatearrayDTO getByCode(String code);
	
	public void insert(TbFormBatearrayDTO tbFormBatearrayDTO);

}

package com.zhongan.dwe.dao.service;


import com.zhongan.dwe.dao.dto.TbExtUserDTO;

public interface TbExtUserService {

	public void add(TbExtUserDTO tbextuserDTO);

	public void update(TbExtUserDTO tbextuserDTO,String id);

	public void delete(String id);

	public TbExtUserDTO getByID(String id);

}

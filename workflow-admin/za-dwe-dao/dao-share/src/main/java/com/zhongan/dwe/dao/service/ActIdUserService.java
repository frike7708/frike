package com.zhongan.dwe.dao.service;


import java.util.List;

import com.zhongan.dwe.dao.dto.ActIdUserDTO;

public interface ActIdUserService {

	public void add(ActIdUserDTO actiduserDTO);

	public void update(ActIdUserDTO actiduserDTO,String id);

	public void delete(String id);

	public ActIdUserDTO getByID_(String id);
	
	public List<ActIdUserDTO> selectUserList();
	
	public List<ActIdUserDTO> selectUserList(int start, int limit);

}

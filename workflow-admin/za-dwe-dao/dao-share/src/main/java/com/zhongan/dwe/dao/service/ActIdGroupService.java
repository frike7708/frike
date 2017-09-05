package com.zhongan.dwe.dao.service;


import java.util.List;

import com.zhongan.dwe.dao.dto.ActIdGroupDTO;

public interface ActIdGroupService {

	public void add(ActIdGroupDTO actidgroupDTO);

	public void update(ActIdGroupDTO actidgroupDTO,String id);

	public void delete(String id);

	public ActIdGroupDTO getByID_(String id);
	
	public List<ActIdGroupDTO> selectGroupList();
	
	public List<ActIdGroupDTO> selectGroupList(int start, int limit);
}

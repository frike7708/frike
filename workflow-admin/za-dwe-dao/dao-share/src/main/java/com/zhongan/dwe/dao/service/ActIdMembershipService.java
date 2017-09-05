package com.zhongan.dwe.dao.service;


import java.util.List;

import com.zhongan.dwe.dao.dto.ActIdMembershipDTO;

public interface ActIdMembershipService {

	public void add(ActIdMembershipDTO actidmembershipDTO);

	public List<ActIdMembershipDTO> getMembership(String groupId,String userId);
}

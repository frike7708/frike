package com.zhongan.dwe.system.service.impl;

import org.springframework.stereotype.Component;

import com.zhongan.dsf.consumer.annotation.Consumer;
import com.zhongan.dwe.dao.dto.ActIdUserDTO;
import com.zhongan.dwe.dao.service.ActIdUserService;
import com.zhongan.dwe.system.model.UserDTO;
import com.zhongan.dwe.system.service.IUserService;

@Component
public class UserServiceImpl implements IUserService {
	
	@Consumer
	private ActIdUserService userService;

	@Override
	public void add(UserDTO dto) {
		ActIdUserDTO actiduserDTO =  new ActIdUserDTO();
		actiduserDTO.setId(dto.getId());
		actiduserDTO.setRev(2);
		actiduserDTO.setFirst(dto.getFirstName());
		actiduserDTO.setLast(dto.getLastName());
		actiduserDTO.setEmail(dto.getEmail());
		actiduserDTO.setPwd(dto.getPwd());
		userService.add(actiduserDTO);
	}

	@Override
	public void update(UserDTO dto) {
		ActIdUserDTO actiduserDTO =  new ActIdUserDTO();
		String id = dto.getId();
		actiduserDTO.setRev(2);
		actiduserDTO.setFirst(dto.getFirstName());
		actiduserDTO.setLast(dto.getLastName());
		actiduserDTO.setEmail(dto.getEmail());
		userService.update(actiduserDTO, id);
	}

}

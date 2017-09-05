package com.zhongan.dwe.system.service.impl;

import java.util.UUID;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.TaskQuery;
import org.springframework.stereotype.Component;

import com.zhongan.dsf.consumer.annotation.Consumer;
import com.zhongan.dwe.dao.dto.TbFormBatearrayDTO;
import com.zhongan.dwe.system.service.IFormService;

@Component
public class FormServiceImpl implements IFormService {
	
	@Consumer(connectTimeoutMillis=20000)
	private  com.zhongan.dwe.dao.service.TbFormBatearrayService formService;
	
	@Override
	public void insertTbFormBatearray(String code, String name, String json) {
		TbFormBatearrayDTO byCode = formService.getByCode(code);
		if(byCode != null){
			throw new RuntimeException("该表单已经存在，请勿重复创建");
		}
		TbFormBatearrayDTO batearrayDTO = new TbFormBatearrayDTO();
		batearrayDTO.setId(UUID.randomUUID().toString());
		batearrayDTO.setCode(code);
		batearrayDTO.setName(name);
		batearrayDTO.setBytes(json.getBytes());
		formService.insert(batearrayDTO);
	}

	@Override
	public void jsonToTable(String code, String name, String json) throws Exception {
		// TODO Auto-generated method stub
	}

}

package com.zhongan.dwe.workflow.controller;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongan.dsf.consumer.annotation.Consumer;

@RestController  
@RequestMapping(value = "/test")
public class TestComtroller {
	
	protected Logger logger = LoggerFactory.getLogger(TestComtroller.class);

	@Consumer(connectTimeoutMillis=20000)
	private com.zhongan.workflow.service.RuntimeService runtimeService;
	
	@RequestMapping  
    public String index(){
		
        return "hello";
    }
	
	
	@RequestMapping(value = "/backTieTi")
	 public String backTieTi() {
		logger.info("理赔系统---》回退铁蹄");
		return "true";
    }
	
}

package com.zhongan.workflow.openapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhongan.workflow.model.ProcessInstance;
import com.zhongan.workflow.service.RuntimeService;

@Controller
@RequestMapping(value = "/process")
public class RuntimeController {
	
	@Autowired
	@Qualifier("runtimeServiceImpl")	
	private RuntimeService service;
	
	
	@RequestMapping(value = { "/start" }, method = RequestMethod.POST)
	public String processStart(@RequestParam("processDefinitionKey") String processDefinitionKey) {
		ProcessInstance processInstance = service.startProcessInstanceByKey(processDefinitionKey);
        return "";
    }
	

}

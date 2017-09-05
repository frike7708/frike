package com.zhongan.workflow.openapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhongan.workflow.exception.TaskException;
import com.zhongan.workflow.model.Task;
import com.zhongan.workflow.model.TaskQuery;
import com.zhongan.workflow.service.TaskService;

@Controller
@RequestMapping(value = "/task")
public class TaskController {
	
	@Autowired
	@Qualifier("taskServiceImpl")	
	private TaskService service;
	
	@RequestMapping(value = { "/claim" }, method = RequestMethod.POST)
	public String taskClaim(@RequestParam("taskId") String taskId,@RequestParam("userId") String userId) {
		try {
			service.claim(taskId, userId);
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "";
    }
	
	@RequestMapping(value = { "/complete" }, method = RequestMethod.POST)
	public String taskComplete(@RequestParam("taskId") String taskId) {
		service.complete(taskId);
        return "";
    }
	
	@RequestMapping(value = { "/query" }, method = RequestMethod.GET)
	public String taskQuery() {
		TaskQuery taskQuery = new TaskQuery();
    	taskQuery.processInstanceId("3222");
    	List<Task> query = service.query(taskQuery);
    	System.out.println(query.size());
        return "";
    }
	
	@RequestMapping(value = { "/task/setOwner" }, method = RequestMethod.POST)
	public String taskSetOwner(@RequestParam("taskId") String taskId,@RequestParam("userId") String userId) {
		service.setOwner(taskId, userId);
        return "";
    }
	
	@RequestMapping(value = { "/mytest" }, method = RequestMethod.GET)
	public String myTest() {
		System.out.println("测试结果   Go Go Go");
        return "";
    }
	

}

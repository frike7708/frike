package com.zhongan.workflow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.zhongan.workflow.model.ProcessInstance;

public class RuntimeServiceImpl implements RuntimeService {

	private static final Logger logger = LoggerFactory.getLogger("runtimeService");

	@Autowired
	protected org.activiti.engine.RuntimeService runtimeService;

	@Autowired
	protected FormService formService;

	@Autowired
	protected org.activiti.engine.TaskService taskService;

	@Autowired
	protected RepositoryService repositoryService;

	@Autowired
	protected IdentityService identityService;

	private static Map<String, ActivityImpl> singleActivity = Maps.newHashMap();

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
		logger.info("开启流程实例  key==" + processDefinitionKey);
		return startProcessInstanceByKey(processDefinitionKey, null, null);
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables) {
		logger.info("开启流程实例  key==" + processDefinitionKey);
		return startProcessInstanceByKey(processDefinitionKey, variables, null);
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables,
			String tenantId) {
		ProcessInstance processInstance = null;
		org.activiti.engine.runtime.ProcessInstance processInstance1 = null;
		if (null == variables) {
			/* 没有流程参数时 */
			if (StringUtils.isNotBlank(tenantId)) {
				processInstance1 = runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, tenantId);
			} else {
				processInstance1 = runtimeService.startProcessInstanceByKey(processDefinitionKey);
			}
		} else {
			/* 有流程参数时 */
			if (StringUtils.isNotBlank(tenantId)) {
				processInstance1 = runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, variables,
						tenantId);
			} else {
				processInstance1 = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
			}
		}
		BeanUtils.copyProperties(processInstance1, processInstance);
		return processInstance;
	}

	@Override
	public List<com.zhongan.workflow.model.ProcessDefinition> processDefinitionQuery() {
		// 流程列表
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().active().list();
		logger.info("查询已部署的流程列表   list.size() ==" + list.size());
		List<com.zhongan.workflow.model.ProcessDefinition> resultList = new ArrayList<com.zhongan.workflow.model.ProcessDefinition>();
		for (ProcessDefinition processDefinition : list) {
			com.zhongan.workflow.model.ProcessDefinition definition = new com.zhongan.workflow.model.ProcessDefinition();
			BeanUtils.copyProperties(processDefinition, definition);
			resultList.add(definition);
		}
		return resultList;
	}

	@Override
	public List<com.zhongan.workflow.model.ProcessDefinition> processDefinitionQuery(int firstResult, int maxResults) {
		// 流程列表
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().active()
				.listPage(firstResult, maxResults);
		logger.info("查询已部署的流程列表   list.size() ==" + list.size());
		List<com.zhongan.workflow.model.ProcessDefinition> resultList = new ArrayList<com.zhongan.workflow.model.ProcessDefinition>();
		for (ProcessDefinition processDefinition : list) {
			com.zhongan.workflow.model.ProcessDefinition definition = new com.zhongan.workflow.model.ProcessDefinition();
			BeanUtils.copyProperties(processDefinition, definition);
			resultList.add(definition);
		}
		return resultList;
	}

	@Override
	public List<ProcessInstance> processInstanceQuery() {
		// 运行中的流程
		List<org.activiti.engine.runtime.ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
		logger.info("运行中的流程列表   list.size() ==" + list.size());
		List<com.zhongan.workflow.model.ProcessInstance> resultList = new ArrayList<com.zhongan.workflow.model.ProcessInstance>();
		for (org.activiti.engine.runtime.ProcessInstance processInstance : list) {
			com.zhongan.workflow.model.ProcessInstance process = new com.zhongan.workflow.model.ProcessInstance();
			BeanUtils.copyProperties(processInstance, process);
			process.setName(getActivityName(processInstance.getProcessDefinitionId(), processInstance.getActivityId()));
			resultList.add(process);
		}
		return resultList;
	}

	@Override
	public List<ProcessInstance> processInstanceQuery(int firstResult, int maxResults) {
		// 运行中的流程
		List<org.activiti.engine.runtime.ProcessInstance> list = runtimeService.createProcessInstanceQuery().listPage(
				firstResult, maxResults);
		logger.info("运行中的流程列表   list.size() ==" + list.size());
		List<com.zhongan.workflow.model.ProcessInstance> resultList = new ArrayList<com.zhongan.workflow.model.ProcessInstance>();
		for (org.activiti.engine.runtime.ProcessInstance processInstance : list) {
			com.zhongan.workflow.model.ProcessInstance process = new com.zhongan.workflow.model.ProcessInstance();
			BeanUtils.copyProperties(processInstance, process);
			process.setName(getActivityName(processInstance.getProcessDefinitionId(), processInstance.getActivityId()));
			resultList.add(process);
		}
		return resultList;
	}

	@SuppressWarnings("deprecation")
	public String getActivityName(String processDefinitionId, String activityId) {
		String cache_key = processDefinitionId + "_" + activityId;
		if (singleActivity.containsKey(cache_key)) {
			logger.info(cache_key + "  命中缓存");
			ActivityImpl activity = singleActivity.get(cache_key);
			return ObjectUtils.toString(activity.getProperty("name"));
		}
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		List<ActivityImpl> activities = processDefinition.getActivities();
		for (ActivityImpl activityImpl : activities) {
			String key = processDefinitionId + "_" + activityImpl.getId();
			if (!singleActivity.containsKey(key)) {
				singleActivity.put(key, activityImpl);
			}
		}
		if (singleActivity.containsKey(cache_key)) {
			logger.info("实例 ： " + processDefinitionId + "存放缓存成功");
			ActivityImpl activity = singleActivity.get(cache_key);
			return ObjectUtils.toString(activity.getProperty("name"));
		}
		return "";
	}

	@Override
	public ProcessInstance startProcessInstanceByMessage(String message, Map<String, Object> variables) {
		return startProcessInstanceByMessage(message, variables, null);
	}

	@Override
	public ProcessInstance startProcessInstanceByMessage(String message, Map<String, Object> variables, String tenantId) {
		ProcessInstance processInstance = null;
		org.activiti.engine.runtime.ProcessInstance processInstance1 = null;
		if (null == variables) {
			/* 没有流程参数时 */
			if (StringUtils.isNotBlank(tenantId)) {
				processInstance1 = runtimeService.startProcessInstanceByMessageAndTenantId(message, tenantId);
			} else {
				processInstance1 = runtimeService.startProcessInstanceByMessage(message);
			}
		} else {
			/* 有流程参数时 */
			if (StringUtils.isNotBlank(tenantId)) {
				processInstance1 = runtimeService
						.startProcessInstanceByMessageAndTenantId(message, variables, tenantId);
			} else {
				processInstance1 = runtimeService.startProcessInstanceByMessage(message, variables);
			}
		}
		BeanUtils.copyProperties(processInstance1, processInstance);
		return processInstance;
	}

	/**
	 * 中止流程实例runId
	 *
	 * @param runId
	 *            流程实例ID
	 * @return
	 */
	@Override
	public boolean abortProcess(String runId) {
		/* 查询流程实例 */
		org.activiti.engine.runtime.ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(runId).singleResult();
		if (null == processInstance) {
			return false;
		}
		runtimeService.deleteProcessInstance(runId, "");
		return true;
	}

	/**
	 * 是否存在runId流程实例
	 *
	 * @param runId
	 *            流程实例ID
	 * @return
	 */
	@Override
	public boolean isExistProcessInstance(String runId) {
		/* 查询流程实例 */
		org.activiti.engine.runtime.ProcessInstance rpi = runtimeService.createProcessInstanceQuery()
				.processInstanceId(runId).singleResult();
		if (null == rpi) {
			return false;
		} else {
			return true;
		}
	}

}

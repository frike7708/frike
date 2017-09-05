package com.zhongan.workflow.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhongan.workflow.exception.ErrorCode;
import com.zhongan.workflow.exception.TaskException;
import com.zhongan.workflow.model.FlowTaskActionDTO;
import com.zhongan.workflow.model.FlowTaskDTO;
import com.zhongan.workflow.model.Task;
import com.zhongan.workflow.model.TaskQuery;
import com.zhongan.comm.util.JsonUtils;

public class TaskServiceImpl implements TaskService {

	private static final Logger logger = LoggerFactory.getLogger("taskService");

	private static final Map<String, String> METHOD_NAME = new HashMap<String, String>();

	@Autowired
	protected org.activiti.engine.TaskService taskService;

	@Autowired
	protected RepositoryService repositoryService;

	@Autowired
	protected FormService formService;

	@Autowired
	protected org.activiti.engine.RuntimeService runtimeService;

	@Override
	public void claim(String taskId, String userId) throws TaskException {
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(taskId)) {
			logger.error("taskId or userId is null");
			throw new TaskException(ErrorCode.PARAM_NULL_POINT, "任务ID及用户不允许为空");
		}
		try {
			logger.info("========= claim claim claim============");
			taskService.claim(taskId, userId);
		} catch (ActivitiTaskAlreadyClaimedException e) {
			logger.error(e.getMessage());
			throw new TaskException(ErrorCode.TASK_ERROR_CODE_200, ErrorCode.TASK_ERROR_CODE_200_MESSAGE);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new TaskException(ErrorCode.UNKNOW_EXCEPTION, ErrorCode.UNKNOW_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public void complete(String taskId) {
		taskService.complete(taskId);
	}

	@Override
	public void complete(String taskId, Map<String, Object> variables) {
		taskService.complete(taskId, variables);
	}

	@Override
	public void complete(String processInstanceId, String receiveTaskId) {
		Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId)
				.activityId(receiveTaskId).singleResult();
		runtimeService.signal(execution.getId());
	}

	@Override
	public List<Task> query(TaskQuery query) {
		List<org.activiti.engine.task.Task> list = new ArrayList<org.activiti.engine.task.Task>();
		// 一个条件的查询
		list = singleParamQuery(query, list);
		logger.info("list.size  == " + list.size());
		List<Task> taskList = new ArrayList<Task>();
		for (org.activiti.engine.task.Task task : list) {
			Task pTask = new Task();
			BeanUtils.copyProperties(task, pTask);
			taskList.add(pTask);
		}
		return taskList;
	}

	/**
	 * 根据参数的数量 查询
	 * 
	 * @param query
	 * @param list
	 * @param filedsInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<org.activiti.engine.task.Task> accordingParamQuery(TaskQuery query,
			List<org.activiti.engine.task.Task> list, List<Map<String, String>> filedsInfo) {
		Map<String, String> map = filedsInfo.get(0);
		String name = map.get("name");
		String value = map.get("value");

		org.activiti.engine.task.TaskQuery createTaskQuery = taskService.createTaskQuery();
		Class<? extends Object> clazz = createTaskQuery.getClass();
		Method method;
		try {
			method = clazz.getMethod("", new Class[] { String.class });
			// 获得参数Object
			Object[] arguments = new Object[] { new String("") };
			list = (List<org.activiti.engine.task.Task>) method.invoke(createTaskQuery, arguments);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<org.activiti.engine.task.Task> singleParamQuery(TaskQuery query,
			List<org.activiti.engine.task.Task> list) {
		org.activiti.engine.task.TaskQuery createTaskQuery = taskService.createTaskQuery();
		String candidateUser = query.getCandidateUser();
		String processInstanceId = query.getProcessInstanceId();
		String owner = query.getOwner();
		String assignee = query.getAssignee();
		String candidateGroup = query.getCandidateGroup();

		logger.info(query.toString());
		// 单一条件查询
		if (!StringUtils.isEmpty(candidateUser) && list.size() == 0) {
			list = createTaskQuery.taskCandidateUser(candidateUser).orderByTaskDueDate().asc().list();
		}

		if (!StringUtils.isEmpty(processInstanceId) && list.size() == 0) {
			list = createTaskQuery.processInstanceId(processInstanceId).orderByTaskDueDate().asc().list();
		}

		if (!StringUtils.isEmpty(owner) && list.size() == 0) {
			list = createTaskQuery.taskOwner(owner).orderByTaskDueDate().asc().list();
		}

		if (!StringUtils.isEmpty(assignee) && list.size() == 0) {
			list = createTaskQuery.taskAssignee(assignee).orderByTaskDueDate().asc().list();
		}

		if (!StringUtils.isEmpty(candidateGroup) && list.size() == 0) {
			list = createTaskQuery.taskCandidateGroup(candidateGroup).orderByTaskDueDate().asc().list();
		}
		return list;
	}

	@Override
	public void setOwner(String taskId, String userId) {
		taskService.setOwner(taskId, userId);
	}

	/**
	 * 根据与流程定义key获取当前节点的下一个任务节点
	 * 
	 * @param key
	 *            流程定义Key
	 * @param elementId当前节点Id
	 * @param elString当前节点流向下一个节点的匹配字符串
	 *            如下 ${deptLeaderPass}--------------------------XML已定义的字符串 ${!deptLeaderPass} 获取领导同意的userTask，则传入
	 *            ${deptLeaderPass}
	 * @return
	 */
	public Object getNextTaskDefinition(String key, String activityId, String elString) {
		ProcessDefinition processDefinition = getNewProcessDefinition(key);// 根据流程定义key获得最新的流程定义信息
		if (processDefinition != null) {
			ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
					.getDeployedProcessDefinition(processDefinition.getId());
			ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);// 当前节点
			if ("userTask".equals(activityImpl.getProperty("type"))) {
				TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl.getActivityBehavior())
						.getTaskDefinition();
				return taskDefinition;
			} else {
				List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
				List<PvmTransition> outpvmTransitions = null;
				for (PvmTransition pvmTransition : pvmTransitions) {
					PvmActivity pa = pvmTransition.getDestination();// 获取所有的终点节点
					if ("exclusiveGateway".equals(pa.getProperty("type"))) {
						outpvmTransitions = pa.getOutgoingTransitions();
						if (outpvmTransitions.size() == 1) {
							return getNextTaskDefinition(key, pa.getId(), elString);
						} else if (outpvmTransitions.size() > 1) {
							for (PvmTransition outPvmTransition : outpvmTransitions) {
								Object object = outPvmTransition.getProperty("conditionText");
								if (elString.equals(StringUtils.trim(object.toString()))) {
									PvmActivity pvmActivity = outPvmTransition.getDestination();
									return getNextTaskDefinition(key, pvmActivity.getId(), elString);
								}
							}
						}
					} else if ("userTask".equals(pa.getProperty("type"))) {
						return ((UserTaskActivityBehavior) ((ActivityImpl) pa).getActivityBehavior())
								.getTaskDefinition();
					}
				}
			}
		}
		return null;
	}

	public ProcessDefinition getNewProcessDefinition(String key) {
		// 根据key查询已经激活的流程定义，并且按照版本进行降序。那么第一个就是将要得到的最新流程定义对象
		List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(key).orderByProcessDefinitionVersion().desc().list();
		if (processDefinitionList.size() > 0) {
			return processDefinitionList.get(0);
		}
		return null;
	}

	@Override
	public FlowTaskDTO completeAndGet(String taskId, Map<String, Object> variables) {
        if (StringUtils.isEmpty(taskId)) {
        	logger.info("completeAndGet taskId is null");
            return null;
        }
        org.activiti.engine.task.Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (null != task) {
            String instId = task.getProcessInstanceId();  //流程实例ID
            complete(taskId, variables);
            return getRunTask(instId);
        }
		return null;
	}
	
    /**
     * 查询出当前任务信息
     *
     * @param instId
     * @return
     */
    @Override
    public List<FlowTaskDTO> getRunTasks(String instId) {
        List<org.activiti.engine.task.Task> taskList = taskService.createTaskQuery().processInstanceId(instId).active().list();
        List<FlowTaskDTO> taskDTOList = new ArrayList<>();
        for (org.activiti.engine.task.Task task : taskList) {
            FlowTaskDTO flowTaskDTO = new FlowTaskDTO();
            flowTaskDTO.setTaskId(task.getId());
            flowTaskDTO.setTaskKey(task.getTaskDefinitionKey());
            flowTaskDTO.setTaskName(task.getName());
            flowTaskDTO.setFromKey(task.getFormKey());
            //
            List<FlowTaskActionDTO> flowTaskActionDTOList = new ArrayList<>();
            TaskFormData formData = formService.getTaskFormData(task.getId());
             List<FormProperty> properties = formData.getFormProperties();
            for (FormProperty property : properties) {
                if (StringUtils.isNotBlank(property.getId())) {
                    FlowTaskActionDTO taskActionDTO = new FlowTaskActionDTO();
                    taskActionDTO.setId(property.getId());
                    taskActionDTO.setName(property.getName());
                    taskActionDTO.setPropertys(JsonUtils.toObject(property.getValue(), Map.class));
                    flowTaskActionDTOList.add(taskActionDTO);
                }
            }
            flowTaskDTO.setTaskActions(flowTaskActionDTOList);
            taskDTOList.add(flowTaskDTO);
        }
        return taskDTOList;
    }

	@Override
	public FlowTaskDTO getRunTask(String instId) {
		List<FlowTaskDTO> tasks = getRunTasks(instId);
        if (null != tasks && tasks.size() > 0)
            return tasks.get(0);
        return null;
	}

    /**
     * 完成消息接收
     * @param msgId
     * @param instId
     */
    @Override
    public boolean completeReceivedMsg(String msgId, String instId, Map<String, Object> variables){
        List<Execution> executionList = runtimeService.createExecutionQuery().processInstanceId(instId).activityId(msgId).list();
        if(executionList.size()>0) {
            for (Execution execution : executionList) {
                if(null==variables){
                    runtimeService.signal(execution.getId());
                }else{
                    runtimeService.signal(execution.getId(),variables);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 完成消息接收
     * @param msgId
     * @param instId
     */
    @Override
    public FlowTaskDTO completeReceivedMsgAndGet(String msgId, String instId, Map<String, Object> variables){
        if (completeReceivedMsg(msgId,instId, variables)) {
            return getRunTask(instId);
        }
        return null;
    }

}

package com.zhongan.workflow.service;

import com.zhongan.workflow.exception.TaskException;
import com.zhongan.workflow.model.FlowTaskDTO;
import com.zhongan.workflow.model.Task;
import com.zhongan.workflow.model.TaskQuery;

import java.util.List;
import java.util.Map;

/**
 * Created by chaikebin on 2017/5/9.
 */
public interface TaskService {

    /**
     * 获取任务
     * @param taskId
     * @param userId
     */
    public void claim(String taskId, String userId) throws TaskException;

    /**
     * 处理任务（完成任务）
     * @param taskId
     */
    public void complete(String taskId);
    
    
    /**
     * 处理任务（完成任务）
     * @param taskId
     */
    public void complete(String taskId, Map<String,Object> variables);
    
    
    /**
     * 完成任务（receiveTask）
     * @param taskId
     */
    public void complete(String processInstanceId, String receiveTaskId);
    
    
    /**
     * 完成任务taskId，variables为流程参数
     * @param taskId    任务ID
     * @param variables 流程参数
     */
    FlowTaskDTO completeAndGet(String taskId, Map<String, Object> variables);
    
    
    /**
     * 完成消息接收
     * @param msgId
     * @param instId
     */
    boolean completeReceivedMsg(String msgId, String instId, Map<String, Object> variables);

    /**
     * 完成消息接收
     * @param msgId
     * @param instId
     */
    FlowTaskDTO completeReceivedMsgAndGet(String msgId, String instId, Map<String, Object> variables);
    
    /**
     * 获取当前运行的Task
     * @param instId
     * @return
     */
    FlowTaskDTO getRunTask(String instId);
    
    /**
     * 查询出当前任务信息
     * @param instId
     * @return
     */
    List<FlowTaskDTO> getRunTasks(String instId);
    
    /**
     * 查询任务列表
     * @param query
     * @return
     */
    public List<Task> query(TaskQuery query);
    

    /**
     * 将任务（taskId）移交给其他用户（userId）
     * @param taskId
     * @param userId
     */
    public void setOwner(String taskId, String userId);
    
}
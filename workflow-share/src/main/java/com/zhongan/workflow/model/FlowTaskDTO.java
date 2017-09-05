package com.zhongan.workflow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenshiyang on 2017/6/27.
 */
public class FlowTaskDTO {

    /**
     * TaskId
     */
    private String taskId;


    /**
     * 节点Key
     */
    private String taskKey;

    /**
     * TaskName
     */
    private String taskName;

    /**
     * FORM
     */
    private String fromKey;

    /**
     * TaskAction
     */
    private List<FlowTaskActionDTO> taskActions=new ArrayList<>();


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getFromKey() {
        return fromKey;
    }

    public void setFromKey(String fromKey) {
        this.fromKey = fromKey;
    }

    public List<FlowTaskActionDTO> getTaskActions() {
        return taskActions;
    }

    public void setTaskActions(List<FlowTaskActionDTO> taskActions) {
        this.taskActions = taskActions;
    }

}

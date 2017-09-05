package com.zhongan.workflow.model;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by chaikebin on 2017/5/9.
 */
public class TaskQuery {
	
	protected String assignee;
	protected String candidateUser;
	protected String processInstanceId;
	protected String owner;
	protected String processInstanceBusinessKey;
	protected String candidateGroup;
	
	protected Map<String,Object> queryMap;
	 
    public TaskQuery taskAssignee(String assignee){
    	this.assignee = assignee;
        return this;
    }
    
    public TaskQuery taskCandidateUser(String candidateUser) {
    	this.candidateUser = candidateUser;
        return this;
    }

    public TaskQuery processInstanceId(String processInstanceId) {
    	this.processInstanceId = processInstanceId;
        return this;
    }

    public TaskQuery taskOwner(String owner) {
    	this.owner = owner;
        return this;
    }
    
    public  TaskQuery processInstanceBusinessKey(String processInstanceBusinessKey){
    	this.processInstanceBusinessKey = processInstanceBusinessKey;
        return this;
    }
	
	public TaskQuery taskCandidateGroup(String candidateGroup){
		this.candidateGroup = candidateGroup;
        return this;
	}

	public String getCandidateUser() {
		return candidateUser;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public String getOwner() {
		return owner;
	}
	
	public String getAssignee() {
		return assignee;
	}
	

	public String getProcessInstanceBusinessKey() {
		return processInstanceBusinessKey;
	}

	public String getCandidateGroup() {
		return candidateGroup;
	}

	public Map<String, Object> getQueryMap() {
		return queryMap;
	}

	@Override
	public String toString() {
		return "TaskQuery [candidateUser=" + candidateUser
				+ ", processInstanceId=" + processInstanceId + ", owner="
				+ owner + "]";
	}
	
    
}
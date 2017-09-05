package com.zhongan.dwe.system.service;

import java.util.*;

/**
 * 工作流跟踪相关Service
 *
 */
public interface IProcessMonitorService {


    /**
     * 流程跟踪图
     *
     * @param processInstanceId 流程实例ID
     * @return 封装了各种节点信息
     */
    public List<Map<String, Object>> traceProcess(String processInstanceId) throws Exception ;


    
	/**
	 * 加载实例信息
	 * @param processInstanceId
	 * @return
	 */
	public Map<String,Object> loadByProcessInstance(String processInstanceId);
}

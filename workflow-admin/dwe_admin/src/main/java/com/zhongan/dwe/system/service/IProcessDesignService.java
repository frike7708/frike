package com.zhongan.dwe.system.service;

import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.repository.Model;

public interface IProcessDesignService {
	
	
	/**
	 * 模型列表
	 * @return
	 */
	public List<Model> selectDesignModes();
	
	/**
	 * 模型列表
	 * @return
	 */
	public List<Model> selectDesignModes(int firstResult, int maxResults);
	
	/**
	 * 创建模型（画流程图）
	 * @return
	 */
	public Model createModel(String name,String key,String description);
	
	
	/**
	 * 删除模板
	 * 
	 * @param modelId
	 */
	public void delete(String modelId) throws Exception;
	
	
	/**
	 * 部署
	 * 
	 * @param modelId
	 */
	public void deploy(String modelId);
	
	
	/**
	 * 导出
	 * 
	 * @param modelId
	 */
	public BpmnModel export(String modelId);

}

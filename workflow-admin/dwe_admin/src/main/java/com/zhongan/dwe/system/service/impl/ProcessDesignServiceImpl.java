package com.zhongan.dwe.system.service.impl;

import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zhongan.dwe.DweAdminApplication;
import com.zhongan.dwe.system.service.IProcessDesignService;

/**
 * 流程设计  控制器实现类
 * @author za-shiyafei
 *
 */
@Component
public class ProcessDesignServiceImpl implements IProcessDesignService {
	
    protected final static Logger logger = LoggerFactory.getLogger(ProcessDesignServiceImpl.class);
	
	@Autowired
    RepositoryService repositoryService;

	@Override
	public List<Model> selectDesignModes() {
		List<Model> list = repositoryService.createModelQuery().list();
		return list;
	}
	
	@Override
	public List<Model> selectDesignModes(int firstResult, int maxResults) {
		List<Model> list = repositoryService.createModelQuery().listPage(firstResult, maxResults);
		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Model createModel(String name, String key, String description) {
		Model modelData = null;
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            //构建新模型
            modelData = repositoryService.newModel();
            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            description = StringUtils.defaultString(description);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(name);
            modelData.setKey(StringUtils.defaultString(key));
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
        } catch (Exception e) {
            logger.error("创建模型失败：", e);
        }
		return modelData;
	}

	@Override
	public void delete(String modelId) throws Exception {
		repositoryService.deleteModel(modelId);
	}

	@Override
	public void deploy(String modelId) {
		try {
            Model modelData = repositoryService.getModel(modelId);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;
            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);
            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
        } catch (Exception e) {
            logger.error("部署流程失败：modelId=" + modelId, e);
            
        }
		
	}

	@Override
	public BpmnModel export(String modelId) {
		 try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());
            JsonNode editorNode = new ObjectMapper().readTree(modelEditorSource);
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            return bpmnModel;
	        } catch (Exception e) {
	            logger.error("导出model的xml文件失败：modelId={}", modelId, e);
	        }
		 return null;
		
	}


}

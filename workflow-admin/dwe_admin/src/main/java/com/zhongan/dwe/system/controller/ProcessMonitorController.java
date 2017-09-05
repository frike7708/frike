package com.zhongan.dwe.system.controller;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhongan.dsf.consumer.annotation.Consumer;
import com.zhongan.dwe.common.page.PageBT;
import com.zhongan.dwe.common.utils.DateUtils;
import com.zhongan.dwe.system.service.IProcessMonitorService;
import com.zhongan.workflow.model.ProcessInstance;

/**
 * 流程监控
 * @author za-shiyafei
 *
 */
@RestController  
@RequestMapping(value = "/process/monitor")
public class ProcessMonitorController {
	
	protected Logger logger = LoggerFactory.getLogger(ProcessMonitorController.class);
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Consumer(connectTimeoutMillis=20000)
	private com.zhongan.workflow.service.RuntimeService runtimeService;
	
	
	@Autowired
	IProcessMonitorService monitorService;
	
	@Autowired
    protected HistoryService historyService;
	
	 /** 
     * 运行中流程列表
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/selectActivitiProcessList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object selectProcessList(@RequestBody PageBT pageBt) throws UnsupportedEncodingException {
    	int page = pageBt.getPage();//当前页
    	int limit = pageBt.getLimit();//条数
    	int start = (page == 1)? 0 :((page -1) *limit);
	    //流程列表
    	int total = runtimeService.processInstanceQuery().size();
	    //流程列表
        List<ProcessInstance> list = runtimeService.processInstanceQuery(start, limit);
        logger.info("查询运行中的流程列表 == " + list.size());
    	JSONArray json = new JSONArray();
    	for (com.zhongan.workflow.model.ProcessInstance processInstance : list) {
    		JSONObject jo = new JSONObject();
            jo.put("id", processInstance.getId());
            jo.put("processInstanceId", processInstance.getProcessInstanceId());
            jo.put("processDefinitionId", processInstance.getProcessDefinitionId());
            jo.put("processDefinitionKey", processInstance.getProcessDefinitionKey());
            jo.put("name", processInstance.getName());
            jo.put("suspended", String.valueOf(processInstance.isSuspended()));//是否挂起
            json.add(jo);
		}
    	JSONObject result = new JSONObject();
    	result.put("total", total);
    	result.put("list", json);
    	return result;
    }
    
    
	/**
	 * 节点轨迹信息
	 * @param processInstanceId
	 * @return
	 */
	@RequestMapping(value = "/process_trace",produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<String, Object>> processTrace(@RequestParam String processInstanceId){
		List<Map<String, Object>> traceProcess = null;
		try {
			traceProcess = monitorService.traceProcess(processInstanceId);
		} catch (Exception e) {
			logger.error("查询节点信息失败",e);
			e.printStackTrace();
		}
		return traceProcess;
	}
	
	
	/**
	 * 加载图片
	 * @param processInstanceId
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/load_pic")
	public void loadPic(@RequestParam String processInstanceId, HttpServletResponse response){
		try {
			Map<String, Object> map = monitorService.loadByProcessInstance(processInstanceId);
			BpmnModel bpmnModel = (BpmnModel)map.get("BpmnModel");
			List<String> activeIds= (List<String>)map.get("activeIds");
			ProcessDiagramGenerator p = new DefaultProcessDiagramGenerator();
			InputStream resourceAsStream = p.generateDiagram(bpmnModel, "png", activeIds, 
					Collections.<String> emptyList(), "宋体", "宋体",null, null, 1.0);
			byte[] b = new byte[1024];
			int len = -1;
			ServletOutputStream outputStream = null;
			while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
				outputStream = response.getOutputStream();
				outputStream.write(b, 0, len);
			}
			outputStream.close();
			resourceAsStream.close();
		} catch (Exception e) {
			logger.error("加载监控图失败",e);
			e.printStackTrace();
		}
		
	}
	
	
	 /** 
     * 运行中流程列表
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/taskDetail",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object detail(@RequestParam String processInstanceId,HttpServletResponse response) throws UnsupportedEncodingException {
	    
    	List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
        logger.info("查询流程实例明细列表 == " + list.size());
    	JSONArray json = new JSONArray();
    	for (HistoricTaskInstance historicTaskInstance : list) {
    		JSONObject jo = new JSONObject();
            jo.put("id", historicTaskInstance.getId());
            jo.put("name", historicTaskInstance.getName());
            jo.put("assignee", historicTaskInstance.getAssignee());
            jo.put("startTime", DateUtils.formatDate(historicTaskInstance.getStartTime(),DATETIME_FORMAT));
            jo.put("endTime", DateUtils.formatDate(historicTaskInstance.getEndTime(),DATETIME_FORMAT));
            json.add(jo);
		}
    	response.setCharacterEncoding("UTF-8");
    	return json;
    }
}

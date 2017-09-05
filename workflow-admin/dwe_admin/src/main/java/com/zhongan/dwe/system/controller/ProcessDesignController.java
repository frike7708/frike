package com.zhongan.dwe.system.controller;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhongan.dwe.common.page.PageBT;
import com.zhongan.dwe.common.utils.CommUtils;
import com.zhongan.dwe.common.utils.DateUtils;
import com.zhongan.dwe.model.Response;
import com.zhongan.dwe.system.service.IProcessDesignService;

/**
 * 流程设计
 * 
 * @author za-shiyafei
 *
 */
@RestController  
@RequestMapping(value = "/process/model")
public class ProcessDesignController {
	
	protected Logger logger = LoggerFactory.getLogger(ProcessDesignController.class);
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	@Autowired
	IProcessDesignService designService;
	
	 /** 
     * 模型列表
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/list",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object selectProcessList(@RequestBody PageBT pageBt) throws UnsupportedEncodingException {
    	int page = pageBt.getPage();//当前页
    	int limit = pageBt.getLimit();//条数
    	int start = (page == 1)? 0 :((page -1) *limit);
	    //流程列表
    	int total = designService.selectDesignModes().size();
    	List<Model> list = designService.selectDesignModes(start, limit);
        logger.info("查询模型列表 == " + list.size());
        JSONArray json = new JSONArray();
    	for (Model model : list) {
    		JSONObject jo = new JSONObject();
            jo.put("id", model.getId());
            jo.put("name", model.getName());
            jo.put("key", model.getKey());
            jo.put("createTime",DateUtils.formatDate(model.getCreateTime(),DATETIME_FORMAT));
            jo.put("lastUpdateTime", DateUtils.formatDate(model.getLastUpdateTime(),DATETIME_FORMAT));//最后更新时间
            jo.put("metaInfo", model.getMetaInfo());//元数据
            json.add(jo);
		}
    	JSONObject result = new JSONObject();
    	result.put("total", total);
    	result.put("list", json);
    	return result;
    }

	/**
     * 创建模型
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Object create(HttpServletRequest request, HttpServletResponse response) {
        try {
        	String json = request.getParameter("param");
    		if(StringUtils.isEmpty(json)){
    			return "error : 请求参数不能为空";
    		}
        	Map<String, String> parseJsonToMap = CommUtils.parseJsonToMap(json);
        	String name = parseJsonToMap.get("name");
        	String key = parseJsonToMap.get("key");
        	if(StringUtils.isEmpty(name) || StringUtils.isEmpty(key)){
        		return "error : 名称或者KEY不能为空";
        	}
        	//描述不是非必填字段
        	String description=parseJsonToMap.get("description");
        	if(StringUtils.isEmpty(description)){
        		description =  "NewModel";
        	}
        	Model modelData = designService.createModel(name, key, description);
        	String resultUrl = "http://" + request.getLocalAddr() +":" + request.getLocalPort() + "/static/modeler.html?modelId=" + modelData.getId();
        	JSONObject responseJson = new JSONObject();
        	responseJson.put("resultUrl", resultUrl);
			return responseJson;
		} catch (Exception e) {
			logger.error("创建模型失败",e);
		}
        return "";
    }
    
    
    /**
     * 根据modelId部署流程
     * @param id
     * @return
     */
    @RequestMapping(value = "/deploy",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object delpoy(@RequestParam String id){
    	Response response = new Response();
    	try{
    		designService.deploy(id);
    	}catch(NullPointerException e){
    		response.setCode(-1);
    		response.setMsg("请勿重复操作");
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("部署失败");
    	}
    	return response.toString();
    }
    
    
    @RequestMapping(value = "/export")
    @ResponseBody
    public Response export(@RequestParam String id,HttpServletRequest request,HttpServletResponse response){
    	Response result = new Response();
    	try{
    		BpmnModel bpmnModel = designService.export(id);
    		// 处理异常
            if (bpmnModel.getMainProcess() == null) {
                result.setCode(-1);
        		result.setMsg("流程图内容为空");
        		return result;
            }
            String filename = "";
            byte[] exportBytes = null;
            String mainProcessId = bpmnModel.getMainProcess().getId();
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            exportBytes = xmlConverter.convertToXML(bpmnModel);
            if(exportBytes.length == 0){
            	result.setCode(-1);
        		result.setMsg("流程图内容为空");
        		return result;
            }
            filename = mainProcessId + ".bpmn20.xml";
            //写文件到本地
            String dir = request.getSession().getServletContext().getRealPath("/") + File.separator +   "BPMFile";
            File file = new File(dir);
            if(!file .exists()  && !file .isDirectory()){
            	file.mkdir();
            }else{
            	boolean success = (new File(dir)).delete();
            	logger.info("删除历史导出文件  " + success);
            }
            File bpmFile = new File(dir + "/" + filename);
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(bpmFile);
            //写入数据
            outStream.write(exportBytes);
            //关闭输出流
            outStream.close();
            result.setResult(filename);
    	}catch(Exception e){
    		result.setCode(-1);
    		result.setMsg("导出失败");
    	}
		return result;
    }
    
    @RequestMapping(value = "/delete",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object delete(@RequestParam String id){
    	Response response = new Response();
    	try{
    		designService.delete(id);
    	}catch(NullPointerException e){
    		response.setCode(-1);
    		response.setMsg("请勿重复操作");
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("删除失败");
    	}
    	return response.toString();
    }
	

	
}

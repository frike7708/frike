package com.zhongan.dwe.system.controller;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhongan.dsf.consumer.annotation.Consumer;
import com.zhongan.dwe.common.page.PageBT;
import com.zhongan.dwe.model.Response;

/**
 * 流程引擎--管理后台
 * @author za-shiyafei
 *
 */
@RestController  
@RequestMapping(value = "/process/deployed")
public class ProcessDeployedController {
	
	protected Logger logger = LoggerFactory.getLogger(ProcessDeployedController.class);

	@Consumer(connectTimeoutMillis=20000)
	private com.zhongan.workflow.service.RuntimeService runtimeService;
	
	@Autowired
    RepositoryService repositoryService;
	
	
	 /** 
     * 已部署的 流程列表
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/selectProcessList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object selectProcessList(@RequestBody PageBT pageBt) throws UnsupportedEncodingException {
    	int page = pageBt.getPage();//当前页
    	int limit = pageBt.getLimit();//条数
    	int start = (page == 1)? 0 :((page -1) *limit);
	    //流程列表
    	int total = runtimeService.processDefinitionQuery().size();
        List<com.zhongan.workflow.model.ProcessDefinition> list = runtimeService.processDefinitionQuery(start, limit);
        logger.info("查询流程列表 == " + list.size());
    	JSONArray json = new JSONArray();
    	for (com.zhongan.workflow.model.ProcessDefinition processDefinition : list) {
    		JSONObject jo = new JSONObject();
            jo.put("id", processDefinition.getId());
            jo.put("name", processDefinition.getName());
            jo.put("key", processDefinition.getKey());
            jo.put("deploymentId", processDefinition.getDeploymentId());
            json.add(jo);
		}
    	JSONObject result = new JSONObject();
    	result.put("total", total);
    	result.put("list", json);
    	return result;
    }
    
    @RequestMapping(value = "/delete",produces = "application/json; charset=utf-8")
    public String delete(@RequestParam String deploymentId){
    	Response response = new Response();
    	try{
    		repositoryService.deleteDeployment(deploymentId, true);
    	}catch(NullPointerException e){
    		response.setCode(-1);
    		response.setMsg("请勿重复操作");
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("删除失败");
    	}
    	return response.toString();
    } 
    
    @RequestMapping(value = "/deploy",produces = "application/json; charset=utf-8")
    public String deploy(@RequestParam("file") MultipartFile file,HttpServletRequest request){
    	Response result = new Response();
    	try{
    		String fileName = file.getOriginalFilename();
            InputStream fileInputStream = file.getInputStream();
            String extension = FilenameUtils.getExtension(fileName);
            Deployment deploy = null;
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deploy = repositoryService.createDeployment().addZipInputStream(zip).deploy();
            } else {
            	deploy = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
            }
            logger.info("部署流程 ：" + deploy.getName() + " 成功");
        }catch(NullPointerException e){
    		result.setCode(-1);
    		result.setMsg("请勿重复操作");
    	}catch(Exception e){
    		result.setCode(-1);
    		result.setMsg("删除失败");
    	}
    	return result.toString();
    } 
    
	
}

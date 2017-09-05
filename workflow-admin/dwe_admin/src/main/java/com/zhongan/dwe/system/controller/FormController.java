package com.zhongan.dwe.system.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhongan.dsf.consumer.annotation.Consumer;
import com.zhongan.dwe.common.page.PageBT;
import com.zhongan.dwe.common.utils.CommUtils;
import com.zhongan.dwe.common.utils.DateUtils;
import com.zhongan.dwe.dao.dto.TbFormBatearrayDTO;
import com.zhongan.dwe.model.Response;
import com.zhongan.dwe.system.service.IFormService;

@RestController  
@RequestMapping(value = "/form")
public class FormController {
	
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	protected Logger logger = LoggerFactory.getLogger(FormController.class);

	@Consumer(connectTimeoutMillis=20000)
	private  com.zhongan.dwe.dao.service.TbFormBatearrayService formService;
	
	@Autowired
    IFormService service;
	
	/** 
     * 已部署的 流程列表
     * @param model
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/list",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object selectFormList(@RequestBody PageBT pageBt) throws UnsupportedEncodingException {
    	int page = pageBt.getPage();//当前页
    	int limit = pageBt.getLimit();//条数
    	int start = (page == 1)? 0 :((page -1) *limit);
    	int total = formService.selectFormList().size();
        List<TbFormBatearrayDTO> list = formService.selectFormList(start, limit);
        logger.info("查询表单列表 == " + list.size());
    	JSONArray json = new JSONArray();
    	for (TbFormBatearrayDTO dto : list) {
    		JSONObject jo = new JSONObject();
            jo.put("id", dto.getId());
            jo.put("code", dto.getCode());
            jo.put("name", dto.getName());
            jo.put("gmtCreated", DateUtils.formatDate(dto.getGmtCreated(),DATETIME_FORMAT));
            jo.put("creator", dto.getCreator());
            jo.put("metaInfo", urlEncodeByte(dto));
            json.add(jo);
		}
    	JSONObject result = new JSONObject();
    	result.put("total", total);
    	result.put("list", json);
    	return result;
    }
    
    public String urlEncodeByte(TbFormBatearrayDTO dto){
    	byte[] bytes = dto.getBytes();
    	if(bytes == null){
        	return "";
        }
    	Object parse = JSONArray.parse(bytes);
    	return URLEncoder.encode(parse.toString());
    }
    
    /**
     * 保存表单
     */
    @RequestMapping(value = "/save/{code}/{name}")
    public Object save(@PathVariable String code,@PathVariable String name,HttpServletRequest request) {
    	Response result = new Response();
    	try {
    		String json = request.getParameter("param");
        	if(StringUtils.isEmpty(json)){
        		result.setCode(-1);
        		result.setMsg("表单内容为空");
        		return result;
        	}
        	service.insertTbFormBatearray(code, name, json);
		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("保存失败");
		}
    	return result;
    }
    
    @RequestMapping(value = "/edit",produces = "application/json; charset=utf-8")
    public Object editForm(@RequestParam String code){
    	Response response = new Response();
    	try{
    		TbFormBatearrayDTO byCode = formService.getByCode(code);
    		byte[] bytes = byCode.getBytes();
        	Object parse = JSONArray.parse(bytes);
        	
    	}catch(NullPointerException e){
    		response.setCode(-1);
    		response.setMsg("请勿重复操作");
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("编辑失败");
    	}
    	return response.toString();
    }
    
    
    @RequestMapping(value = "/delete",produces = "application/json; charset=utf-8")
    public Object delete(@RequestParam String id){
    	Response response = new Response();
    	try{
    		formService.delete(id);
    	}catch(NullPointerException e){
    		response.setCode(-1);
    		response.setMsg("请勿重复操作");
    	}catch(Exception e){
    		response.setCode(-1);
    		response.setMsg("删除失败");
    	}
    	return response.toString();
    }
    
    
    @RequestMapping(value = "/copyToClipboard")
    public Object copyToClipboard(HttpServletRequest request){
    	Response response = new Response();
    	String json = request.getParameter("param");
    	if(StringUtils.isEmpty(json)){
    		response.setCode(-1);
    		response.setMsg("复制内容为空");
    		return response.toString();
    	}
    	JSONObject parse = (JSONObject) JSONObject.parse(json);
    	String contents = parse.getString("text");
    	StringSelection stringSelection = new StringSelection(contents);
		// 系统剪贴板
		Clipboard  clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
    	
    	//CommUtils.setClipbordContents(contents);
    	return response.toString();
    }

}

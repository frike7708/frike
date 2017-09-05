package com.zhongan.claim.flow.exec;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.el.FixedValue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhongan.comm.cache.InitDataCache;
import com.zhongan.comm.util.HttpClientUtil;
import com.zhongan.dao.ServerUrlDao;

/**
 * 执行器
 * Created by chenshiyang on 2017/7/7.
 */
public class HttpExecService implements JavaDelegate {
	
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger("HttpExecService");
    
    @Autowired
    ServerUrlDao dao;
    
    private String url;

    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(FixedValue url) {
        this.url = url.getExpressionText();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(FixedValue method) {
        this.method = method.getExpressionText();
    }

    @Override
    public void execute(DelegateExecution execution) {
    	//数据库配置  根据流程的定义ID + 租户ID 获取目标serverUrl
    	String processDefinitionId = execution.getProcessDefinitionId() ;
    	String tenantId = execution.getTenantId();
        String key = processDefinitionId + "-" + tenantId;
    	String serverUrl = getServerUrl(key);
    	if(StringUtils.isEmpty(serverUrl)){
    		logger.error("ServiceTask  调用Http接口地址未获取到     serverUrl == " + serverUrl);
    		return ;
    	}
        url = serverUrl + url;
        if(StringUtils.isNotBlank(url)) {
            if (StringUtils.isNoneBlank(method))
                method = "get";
            //调用
            try {
                if ("get" == method.trim().toLowerCase()) {
                    for (Map.Entry entry : execution.getVariables().entrySet()) {
                        url.replaceAll("{" + entry.getKey() + "}", String.valueOf(entry.getValue()));
                    }
                    //
                   HttpClientUtil.Get(url);
                } else {
                    HttpClientUtil.Post(url, String.valueOf(execution.getVariable("data")));
                }

            } catch (Exception ex) {
                logger.error("ServiceTask 调用出错:" , ex);
            }
        }
        else
        {
            //
            logger.error("ServiceTask-HttpExec参数丢失，url:{},method:{}", url, method);
        }

    }

	private String getServerUrl(String key) {
		if(InitDataCache.serverMap.containsKey(key)){
			return InitDataCache.serverMap.get(key);
		}
		logger.info("HttpExecService  key == " + key + "  查询配置URL");
		List<Map<String, Object>> selectServerUrls = dao.selectServerUrls(key);
		if(selectServerUrls.size() >= 1){
			Map<String, Object> map = selectServerUrls.get(0);
			String url = (String) map.get("URL");
			if(!InitDataCache.serverMap.containsKey(key)){
				InitDataCache.serverMap.put(key, url);
			}
			return (String)selectServerUrls.get(0).get("URL");
		}
		return null;
	}

}

package com.zhongan.comm.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zhongan.dao.ServerUrlDao;

/**
 * 服务启动加载数据库缓存
 * 
 * @author za-shiyafei
 *
 */
@Component
public class InitDataCache implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger("InitDataCache");
	
	public static Map<String,String> serverMap = new ConcurrentHashMap<String, String>();
    
    @Autowired
    private ServerUrlDao dao;

	@Override
	public void run(String... arg0) throws Exception {
		//查询数据库，缓存数据
		List<Map<String, Object>> selectServerUrls = dao.selectServerUrls("");
		logger.info("初始化数据，缓存  size==" + selectServerUrls.size());
		for (Map<String, Object> map : selectServerUrls) {
			String key = (String) map.get("CKEY");
			String url = (String) map.get("URL");
			if(!serverMap.containsKey(key)){
				serverMap.put(key, url);
			}
		}
		logger.info("serverMap == " + serverMap.toString());
	}

}

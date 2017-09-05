package com.zhongan.comm.util;


import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 *Json工具类
 * Created by chenshiyang on 2016/7/21.
 */
public class JsonUtils  {

	private final static JsonMapper objectMapper = new JsonMapper();

	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private JsonUtils() {}

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			objectMapper.writeValue(out, obj);
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug("JSON 转化出错");
		}
		String json = "";
		try {
			json = new String(out.toByteArray(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static <T> T toObject(String json, Class<T> cls) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return objectMapper.readValue(json, cls);
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug("JSON 转化出错, JSON字符串:{}", json);
			return null;
		}
	}


}

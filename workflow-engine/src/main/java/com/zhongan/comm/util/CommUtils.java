package com.zhongan.comm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CommUtils {

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 *
	 * */
	public static List<Map<String, String>> getFiledsInfo(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			String fieldValueByName = getFieldValueByName(fields[i].getName(),
					obj);
			if (StringUtils.isEmpty(fieldValueByName)
					|| "null".equals(fieldValueByName)) {
				continue;
			}
			infoMap = new HashMap<String, String>();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), obj));
			list.add(infoMap);
		}
		return list;
	}

	/**
	 * 根据属性名获取属性值
	 * */
	public static String getFieldValueByName(String fieldName, Object obj) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = obj.getClass().getMethod(getter, new Class[] {});
			String value = (String) method.invoke(obj, new Object[] {});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 连接rest服务
	 * @param targetURL
	 * @return
	 */
	public static String httpToRest(String targetURL){
		String output = "";
		try {
			URL targetUrl = new URL(targetURL);
			HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.flush();
			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
			}
			BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
			while ((output = responseBuffer.readLine()) != null) {
				return output;
			}
			httpConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * 连接rest服务
	 * @param targetURL
	 * @return
	 */
	public static String httpToRest(String targetURL,Object input){
		String output = "";
		try {
			URL targetUrl = new URL(targetURL);
			HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(((String) input).getBytes());
			outputStream.flush();
			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
			}
			BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
			while ((output = responseBuffer.readLine()) != null) {
				return output;
			}
			httpConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
	
}

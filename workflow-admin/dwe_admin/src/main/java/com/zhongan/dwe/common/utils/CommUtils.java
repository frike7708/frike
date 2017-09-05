package com.zhongan.dwe.common.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class CommUtils {
	
	/**
	 * 解析json To Map
	 * @param json
	 * @return
	 */
	public static Map<String,String> parseJsonToMap(String json) {
		Map<String,String> map = new HashMap<String,String>();
		JSONObject parse = JSONObject.parseObject(json);
		for(java.util.Map.Entry<String,Object> entry:parse.entrySet()){
			map.put(entry.getKey(), (String)entry.getValue());
		}
		return map;
	}
	
	
	/**
	 * 判断对象属性是否为空
	 * @param obj
	 * @return
	 * @throws IllegalAccessException
	 */
	public static boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
	    boolean flag = false;
	    for(Field f : obj.getClass().getDeclaredFields()){
	        f.setAccessible(true);
	        if(f.get(obj) == null){
	            flag = true;
	            return flag;
	        }
	    }
	    return flag;
	}
	
	 /**
	  * 向剪贴板中添加内容
	  * 
	  * @param content
	  */
	 public static void setClipbordContents(String contents) {
		  StringSelection stringSelection = new StringSelection(contents);
		  // 系统剪贴板
		  Clipboard  clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		  clipboard.setContents(stringSelection, null);
	 }
}

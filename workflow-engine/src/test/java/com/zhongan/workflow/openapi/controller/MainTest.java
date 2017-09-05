package com.zhongan.workflow.openapi.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zhongan.workflow.model.TaskQuery;

public class MainTest {

	public static void main(String[] args) {
		/*TaskQuery query = new TaskQuery();
		query.taskAssignee("taskAssignee").processInstanceBusinessKey("processInstanceBusinessKey").taskCandidateUser("taskCandidateUser");
		List filedsInfo = getFiledsInfo(query);
		System.out.println(filedsInfo.size());
		for (Object object : filedsInfo) {
			System.out.println(object);
		}*/
		
		String[] atp = {"Rafael Nadal", "Novak Djokovic",   
			    "Stanislas Wawrinka", "David Ferrer",  
			    "Roger Federer", "Andy Murray",  
			    "Tomas Berdych", "Juan Martin Del Potro",  
			    "Richard Gasquet", "John Isner"};
		Arrays.sort(atp, (String s1, String s2) -> (s1.compareTo(s2)));
		List<String> players =  Arrays.asList(atp);
		// 使用 lambda 表达式以及函数操作(functional operation)  
		players.forEach((str) -> System.out.println(str));
			
	}

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 *
	 * */
	private static List<Map<String,String>> getFiledsInfo(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			String fieldValueByName = getFieldValueByName(fields[i].getName(), obj);
			if(StringUtils.isEmpty(fieldValueByName) || "null".equals(fieldValueByName)){
				continue;
			}
			infoMap = new HashMap<String,String>();
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
	   private static String getFieldValueByName(String fieldName, Object o) {  
	       try {    
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	           String getter = "get" + firstLetter + fieldName.substring(1);    
	           Method method = o.getClass().getMethod(getter, new Class[] {});    
	           String value = (String) method.invoke(o, new Object[] {});
	           return value;    
	       } catch (Exception e) {    
	           return null;    
	       }    
	   }  
}

package com.zhongan.dwe.system.service;

public interface IFormService {

	/**
	 * 表单数据入库
	 * @param code
	 * @param name
	 * @param json
	 */
	public void insertTbFormBatearray(String code,String name,String json);
	
	
	
	/**
	 * 生成数据库表
	 * @param code
	 * @param name
	 * @param json
	 * @throws Exception 
	 */
	public void jsonToTable(String code,String name,String json) throws Exception;

}

package com.zhongan.dwe.model;

import com.alibaba.fastjson.JSONObject;


public class Response {
	
	private int code = Code.SUCCESS_CODE;
	  
	private String msg = "操作成功";
	  
	private Object result;
	  
	public Response() {
		  
	}
	
	public Response(int code, String msg) {
	    this.code = code;
	    this.msg = msg;
	}
	
	public Response(Object result) {
	    this.result = result;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", this.code);
		jsonObject.put("msg", this.msg);
		jsonObject.put("result", this.result);
		return jsonObject.toString();
	}

}

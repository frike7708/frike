package com.zhongan.workflow.exception;


public class ProcessInstanceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;
	
	
	public ProcessInstanceException(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		return "ProcessInstanceException [code=" + code + ", message="
				+ message + "]";
	}
	
	
}
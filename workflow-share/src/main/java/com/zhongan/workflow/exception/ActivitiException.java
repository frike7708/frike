package com.zhongan.workflow.exception;

public class ActivitiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private int code;
	private String message;
	
	public ActivitiException(int code, String message) {
		super();
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
		return "ActivitiException [code=" + code + ", message=" + message + "]";
	}
	
}

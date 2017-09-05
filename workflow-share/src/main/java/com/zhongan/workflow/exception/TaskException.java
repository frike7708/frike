package com.zhongan.workflow.exception;

public class TaskException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	private int code;
	private String message;
	
	public TaskException(int code, String message) {
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
		return "TaskException [code=" + code + ", message=" + message + "]";
	}
	
}

package com.zhongan.dwe.dao.vo;


public class TbErrorLogVO {
private String id;
private String errorCode;
private String errorType;
private String message;
 
public void setId(String id) {
	this.id = id;
}

public String getId() {
	return this.id;
}
public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
}

public String getErrorCode() {
	return this.errorCode;
}
public void setErrorType(String errorType) {
	this.errorType = errorType;
}

public String getErrorType() {
	return this.errorType;
}
public void setMessage(String message) {
	this.message = message;
}

public String getMessage() {
	return this.message;
}
 @Override
public String toString() {
return "TbErrorLogVO [id=" + id + " ,errorCode=" + errorCode + " ,errorType=" + errorType + " ,message=" + message + " ]";
 }
}

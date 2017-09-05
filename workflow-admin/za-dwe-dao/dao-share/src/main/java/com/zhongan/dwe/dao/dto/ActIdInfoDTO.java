package com.zhongan.dwe.dao.dto;

public class ActIdInfoDTO {
private String id;
private Integer rev;
private String userId;
private String type;
private String key;
private String value;
private byte[] password;
private String parentId;
 
public void setId(String id) {
	this.id = id;
}

public String getId() {
	return this.id;
}
public void setRev(Integer rev) {
	this.rev = rev;
}

public Integer getRev() {
	return this.rev;
}
public void setUserId(String userId) {
	this.userId = userId;
}

public String getUserId() {
	return this.userId;
}
public void setType(String type) {
	this.type = type;
}

public String getType() {
	return this.type;
}
public void setKey(String key) {
	this.key = key;
}

public String getKey() {
	return this.key;
}
public void setValue(String value) {
	this.value = value;
}

public String getValue() {
	return this.value;
}
public void setPassword(byte[] password) {
	this.password = password;
}

public byte[] getPassword() {
	return this.password;
}
public void setParentId(String parentId) {
	this.parentId = parentId;
}

public String getParentId() {
	return this.parentId;
}
 @Override
public String toString() {
return "ActIdInfoDTO [id=" + id + " ,rev=" + rev + " ,userId=" + userId + " ,type=" + type + " ,key=" + key + " ,value=" + value + " ,password=" + password + " ,parentId=" + parentId + " ]";
 }
}

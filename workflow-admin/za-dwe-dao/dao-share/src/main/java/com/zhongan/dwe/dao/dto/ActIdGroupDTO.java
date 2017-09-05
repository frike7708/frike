package com.zhongan.dwe.dao.dto;


public class ActIdGroupDTO {
private String id;
private Integer rev;
private String name;
private String type;
 
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
public void setName(String name) {
	this.name = name;
}

public String getName() {
	return this.name;
}
public void setType(String type) {
	this.type = type;
}

public String getType() {
	return this.type;
}
 @Override
public String toString() {
return "ActIdGroupDTO [id=" + id + " ,rev=" + rev + " ,name=" + name + " ,type=" + type + " ]";
 }
}

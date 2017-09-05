package com.zhongan.dwe.dao.vo;

import java.util.Date;

public class TbFormBatearrayVO {
private String id;
private String code;
private String name;
private byte[] bytes;
private Date gmtCreated;
private Date gmtModified;
private String creator;
private String modifier;
private String isDeleted;
private String remark;
 
public void setId(String id) {
	this.id = id;
}

public String getId() {
	return this.id;
}
public void setCode(String code) {
	this.code = code;
}

public String getCode() {
	return this.code;
}
public void setName(String name) {
	this.name = name;
}

public String getName() {
	return this.name;
}
public void setBytes(byte[] bytes) {
	this.bytes = bytes;
}

public byte[] getBytes() {
	return this.bytes;
}
public void setGmtCreated(Date gmtCreated) {
	this.gmtCreated = gmtCreated;
}

public Date getGmtCreated() {
	return this.gmtCreated;
}
public void setGmtModified(Date gmtModified) {
	this.gmtModified = gmtModified;
}

public Date getGmtModified() {
	return this.gmtModified;
}
public void setCreator(String creator) {
	this.creator = creator;
}

public String getCreator() {
	return this.creator;
}
public void setModifier(String modifier) {
	this.modifier = modifier;
}

public String getModifier() {
	return this.modifier;
}
public void setIsDeleted(String isDeleted) {
	this.isDeleted = isDeleted;
}

public String getIsDeleted() {
	return this.isDeleted;
}
public void setRemark(String remark) {
	this.remark = remark;
}

public String getRemark() {
	return this.remark;
}
 @Override
public String toString() {
return "TbFormBatearrayVO [id=" + id + " ,code=" + code + " ,name=" + name + " ,bytes=" + bytes + " ,gmtCreated=" + gmtCreated + " ,gmtModified=" + gmtModified + " ,creator=" + creator + " ,modifier=" + modifier + " ,isDeleted=" + isDeleted + " ,remark=" + remark + " ]";
 }
}

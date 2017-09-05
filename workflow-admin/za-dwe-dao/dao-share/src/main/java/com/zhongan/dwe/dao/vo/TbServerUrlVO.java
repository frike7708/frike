package com.zhongan.dwe.dao.vo;

import java.util.Date;

public class TbServerUrlVO {
private String id;
private String ckey;
private String url;
private Integer version;
private Date gmtCreated;
private Date gmtModified;
private String creator;
private String modifier;
private String isDeleted;
 
public void setId(String id) {
	this.id = id;
}

public String getId() {
	return this.id;
}
public void setCkey(String ckey) {
	this.ckey = ckey;
}

public String getCkey() {
	return this.ckey;
}
public void setUrl(String url) {
	this.url = url;
}

public String getUrl() {
	return this.url;
}
public void setVersion(Integer version) {
	this.version = version;
}

public Integer getVersion() {
	return this.version;
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
 @Override
public String toString() {
return "TbServerUrlVO [id=" + id + " ,ckey=" + ckey + " ,url=" + url + " ,version=" + version + " ,gmtCreated=" + gmtCreated + " ,gmtModified=" + gmtModified + " ,creator=" + creator + " ,modifier=" + modifier + " ,isDeleted=" + isDeleted + " ]";
 }
}

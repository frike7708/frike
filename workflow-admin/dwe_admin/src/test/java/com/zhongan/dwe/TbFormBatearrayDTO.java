package com.zhongan.dwe;

import java.util.Date;

public class TbFormBatearrayDTO {
private String id;
private String code;
private String name;
private byte[] bytes;
private Date addtime;
private Date motifytime;
private String createuser;
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
public void setAddtime(Date addtime) {
	this.addtime = addtime;
}

public Date getAddtime() {
	return this.addtime;
}
public void setMotifytime(Date motifytime) {
	this.motifytime = motifytime;
}

public Date getMotifytime() {
	return this.motifytime;
}
public void setCreateuser(String createuser) {
	this.createuser = createuser;
}

public String getCreateuser() {
	return this.createuser;
}
public void setRemark(String remark) {
	this.remark = remark;
}

public String getRemark() {
	return this.remark;
}
 @Override
public String toString() {
return "TbFormBatearrayDTO [id=" + id + " ,code=" + code + " ,name=" + name + " ,bytes=" + bytes + " ,addtime=" + addtime + " ,motifytime=" + motifytime + " ,createuser=" + createuser + " ,remark=" + remark + " ]";
 }
}

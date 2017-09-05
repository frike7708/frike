package com.zhongan.dwe.dao.vo;


public class TbExtUserVO {
private String id;
private Integer rev;
private String first;
private String last;
private String email;
private String pwd;
 
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
public void setFirst(String first) {
	this.first = first;
}

public String getFirst() {
	return this.first;
}
public void setLast(String last) {
	this.last = last;
}

public String getLast() {
	return this.last;
}
public void setEmail(String email) {
	this.email = email;
}

public String getEmail() {
	return this.email;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}

public String getPwd() {
	return this.pwd;
}
 @Override
public String toString() {
return "TbExtUserVO [id=" + id + " ,rev=" + rev + " ,first=" + first + " ,last=" + last + " ,email=" + email + " ,pwd=" + pwd + " ]";
 }
}

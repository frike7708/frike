package com.zhongan.dwe.dao.dto;


public class ActIdMembershipDTO {
private String userId;
private String groupId;
 
public void setUserId(String userId) {
	this.userId = userId;
}

public String getUserId() {
	return this.userId;
}
public void setGroupId(String groupId) {
	this.groupId = groupId;
}

public String getGroupId() {
	return this.groupId;
}
 @Override
public String toString() {
return "ActIdMembershipDTO [userId=" + userId + " ,groupId=" + groupId + " ]";
 }
}

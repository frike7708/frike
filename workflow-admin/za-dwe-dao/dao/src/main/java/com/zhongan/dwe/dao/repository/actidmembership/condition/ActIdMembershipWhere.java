package com.zhongan.dwe.dao.repository.actidmembership.condition;
import java.util.ArrayList;
import java.util.List;
import com.zhongan.dwe.dao.dataobject.ActIdMembership;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ActIdMembershipWhere extends ActIdMembership {
private Boolean userIdIsNull;
private String userIdNotEqual;
private List<String> inUserId;
private List<String> notInUserId;
private String likeUserId;
private String leftLikeUserId;
private String rightLikeUserId;
private String notLikeUserId;
private String notLeftLikeUserId;
private String notRightLikeUserId;
private String equalAndLessThanUserId;
private String equalAndGreaterThanUserId;
private String lessThanUserId;
private String greaterThanUserId;
private Boolean groupIdIsNull;
private String groupIdNotEqual;
private List<String> inGroupId;
private List<String> notInGroupId;
private String likeGroupId;
private String leftLikeGroupId;
private String rightLikeGroupId;
private String notLikeGroupId;
private String notLeftLikeGroupId;
private String notRightLikeGroupId;
private String equalAndLessThanGroupId;
private String equalAndGreaterThanGroupId;
private String lessThanGroupId;
private String greaterThanGroupId;
public void inUserIdList(List<String> list) {
       this.inUserId = list;
 }
public void notInUserIdList(List<String> list) {
       this.notInUserId = list;
 }
public void addInUserId(String userId) {
		if (inUserId ==null) {
			inUserId = new ArrayList<String>();
		}
		inUserId.add(userId);
	}
public void addNotInUserId(String userId) {
		if (notInUserId ==null) {
			notInUserId = new ArrayList<String>();
		}
		notInUserId.add(userId);
	}
public void inGroupIdList(List<String> list) {
       this.inGroupId = list;
 }
public void notInGroupIdList(List<String> list) {
       this.notInGroupId = list;
 }
public void addInGroupId(String groupId) {
		if (inGroupId ==null) {
			inGroupId = new ArrayList<String>();
		}
		inGroupId.add(groupId);
	}
public void addNotInGroupId(String groupId) {
		if (notInGroupId ==null) {
			notInGroupId = new ArrayList<String>();
		}
		notInGroupId.add(groupId);
	}
}

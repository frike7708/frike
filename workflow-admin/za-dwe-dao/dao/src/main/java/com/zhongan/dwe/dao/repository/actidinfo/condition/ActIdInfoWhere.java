package com.zhongan.dwe.dao.repository.actidinfo.condition;
import java.util.ArrayList;
import java.util.List;
import com.zhongan.dwe.dao.dataobject.ActIdInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ActIdInfoWhere extends ActIdInfo {
private Boolean idIsNull;
private String idNotEqual;
private List<String> inId;
private List<String> notInId;
private String likeId;
private String leftLikeId;
private String rightLikeId;
private String notLikeId;
private String notLeftLikeId;
private String notRightLikeId;
private String equalAndLessThanId;
private String equalAndGreaterThanId;
private String lessThanId;
private String greaterThanId;
private Boolean revIsNull;
private Integer revNotEqual;
private List<Integer> inRev;
private List<Integer> notInRev;
private Integer equalAndLessThanRev;
private Integer equalAndGreaterThanRev;
private Integer lessThanRev;
private Integer greaterThanRev;
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
private Boolean typeIsNull;
private String typeNotEqual;
private List<String> inType;
private List<String> notInType;
private String likeType;
private String leftLikeType;
private String rightLikeType;
private String notLikeType;
private String notLeftLikeType;
private String notRightLikeType;
private String equalAndLessThanType;
private String equalAndGreaterThanType;
private String lessThanType;
private String greaterThanType;
private Boolean keyIsNull;
private String keyNotEqual;
private List<String> inKey;
private List<String> notInKey;
private String likeKey;
private String leftLikeKey;
private String rightLikeKey;
private String notLikeKey;
private String notLeftLikeKey;
private String notRightLikeKey;
private String equalAndLessThanKey;
private String equalAndGreaterThanKey;
private String lessThanKey;
private String greaterThanKey;
private Boolean valueIsNull;
private String valueNotEqual;
private List<String> inValue;
private List<String> notInValue;
private String likeValue;
private String leftLikeValue;
private String rightLikeValue;
private String notLikeValue;
private String notLeftLikeValue;
private String notRightLikeValue;
private String equalAndLessThanValue;
private String equalAndGreaterThanValue;
private String lessThanValue;
private String greaterThanValue;
private Boolean passwordIsNull;
private byte[] passwordNotEqual;
private List<byte[]> inPassword;
private List<byte[]> notInPassword;
private byte[] equalAndLessThanPassword;
private byte[] equalAndGreaterThanPassword;
private byte[] lessThanPassword;
private byte[] greaterThanPassword;
private Boolean parentIdIsNull;
private String parentIdNotEqual;
private List<String> inParentId;
private List<String> notInParentId;
private String likeParentId;
private String leftLikeParentId;
private String rightLikeParentId;
private String notLikeParentId;
private String notLeftLikeParentId;
private String notRightLikeParentId;
private String equalAndLessThanParentId;
private String equalAndGreaterThanParentId;
private String lessThanParentId;
private String greaterThanParentId;
public void inIdList(List<String> list) {
       this.inId = list;
 }
public void notInIdList(List<String> list) {
       this.notInId = list;
 }
public void addInId(String id) {
		if (inId ==null) {
			inId = new ArrayList<String>();
		}
		inId.add(id);
	}
public void addNotInId(String id) {
		if (notInId ==null) {
			notInId = new ArrayList<String>();
		}
		notInId.add(id);
	}
public void inRevList(List<Integer> list) {
       this.inRev = list;
 }
public void notInRevList(List<Integer> list) {
       this.notInRev = list;
 }
public void addInRev(Integer rev) {
		if (inRev ==null) {
			inRev = new ArrayList<Integer>();
		}
		inRev.add(rev);
	}
public void addNotInRev(Integer rev) {
		if (notInRev ==null) {
			notInRev = new ArrayList<Integer>();
		}
		notInRev.add(rev);
	}
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
public void inTypeList(List<String> list) {
       this.inType = list;
 }
public void notInTypeList(List<String> list) {
       this.notInType = list;
 }
public void addInType(String type) {
		if (inType ==null) {
			inType = new ArrayList<String>();
		}
		inType.add(type);
	}
public void addNotInType(String type) {
		if (notInType ==null) {
			notInType = new ArrayList<String>();
		}
		notInType.add(type);
	}
public void inKeyList(List<String> list) {
       this.inKey = list;
 }
public void notInKeyList(List<String> list) {
       this.notInKey = list;
 }
public void addInKey(String key) {
		if (inKey ==null) {
			inKey = new ArrayList<String>();
		}
		inKey.add(key);
	}
public void addNotInKey(String key) {
		if (notInKey ==null) {
			notInKey = new ArrayList<String>();
		}
		notInKey.add(key);
	}
public void inValueList(List<String> list) {
       this.inValue = list;
 }
public void notInValueList(List<String> list) {
       this.notInValue = list;
 }
public void addInValue(String value) {
		if (inValue ==null) {
			inValue = new ArrayList<String>();
		}
		inValue.add(value);
	}
public void addNotInValue(String value) {
		if (notInValue ==null) {
			notInValue = new ArrayList<String>();
		}
		notInValue.add(value);
	}
public void inPasswordList(List<byte[]> list) {
       this.inPassword = list;
 }
public void notInPasswordList(List<byte[]> list) {
       this.notInPassword = list;
 }
public void addInPassword(byte[] password) {
		if (inPassword ==null) {
			inPassword = new ArrayList<byte[]>();
		}
		inPassword.add(password);
	}
public void addNotInPassword(byte[] password) {
		if (notInPassword ==null) {
			notInPassword = new ArrayList<byte[]>();
		}
		notInPassword.add(password);
	}
public void inParentIdList(List<String> list) {
       this.inParentId = list;
 }
public void notInParentIdList(List<String> list) {
       this.notInParentId = list;
 }
public void addInParentId(String parentId) {
		if (inParentId ==null) {
			inParentId = new ArrayList<String>();
		}
		inParentId.add(parentId);
	}
public void addNotInParentId(String parentId) {
		if (notInParentId ==null) {
			notInParentId = new ArrayList<String>();
		}
		notInParentId.add(parentId);
	}
}

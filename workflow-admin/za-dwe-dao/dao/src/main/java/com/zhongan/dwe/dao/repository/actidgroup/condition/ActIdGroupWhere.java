package com.zhongan.dwe.dao.repository.actidgroup.condition;
import java.util.ArrayList;
import java.util.List;
import com.zhongan.dwe.dao.dataobject.ActIdGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ActIdGroupWhere extends ActIdGroup {
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
private Boolean nameIsNull;
private String nameNotEqual;
private List<String> inName;
private List<String> notInName;
private String likeName;
private String leftLikeName;
private String rightLikeName;
private String notLikeName;
private String notLeftLikeName;
private String notRightLikeName;
private String equalAndLessThanName;
private String equalAndGreaterThanName;
private String lessThanName;
private String greaterThanName;
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
public void inNameList(List<String> list) {
       this.inName = list;
 }
public void notInNameList(List<String> list) {
       this.notInName = list;
 }
public void addInName(String name) {
		if (inName ==null) {
			inName = new ArrayList<String>();
		}
		inName.add(name);
	}
public void addNotInName(String name) {
		if (notInName ==null) {
			notInName = new ArrayList<String>();
		}
		notInName.add(name);
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
}

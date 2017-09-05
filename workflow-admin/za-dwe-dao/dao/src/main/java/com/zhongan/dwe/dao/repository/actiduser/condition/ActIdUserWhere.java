package com.zhongan.dwe.dao.repository.actiduser.condition;
import java.util.ArrayList;
import java.util.List;
import com.zhongan.dwe.dao.dataobject.ActIdUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ActIdUserWhere extends ActIdUser {
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
private Boolean firstIsNull;
private String firstNotEqual;
private List<String> inFirst;
private List<String> notInFirst;
private String likeFirst;
private String leftLikeFirst;
private String rightLikeFirst;
private String notLikeFirst;
private String notLeftLikeFirst;
private String notRightLikeFirst;
private String equalAndLessThanFirst;
private String equalAndGreaterThanFirst;
private String lessThanFirst;
private String greaterThanFirst;
private Boolean lastIsNull;
private String lastNotEqual;
private List<String> inLast;
private List<String> notInLast;
private String likeLast;
private String leftLikeLast;
private String rightLikeLast;
private String notLikeLast;
private String notLeftLikeLast;
private String notRightLikeLast;
private String equalAndLessThanLast;
private String equalAndGreaterThanLast;
private String lessThanLast;
private String greaterThanLast;
private Boolean emailIsNull;
private String emailNotEqual;
private List<String> inEmail;
private List<String> notInEmail;
private String likeEmail;
private String leftLikeEmail;
private String rightLikeEmail;
private String notLikeEmail;
private String notLeftLikeEmail;
private String notRightLikeEmail;
private String equalAndLessThanEmail;
private String equalAndGreaterThanEmail;
private String lessThanEmail;
private String greaterThanEmail;
private Boolean pwdIsNull;
private String pwdNotEqual;
private List<String> inPwd;
private List<String> notInPwd;
private String likePwd;
private String leftLikePwd;
private String rightLikePwd;
private String notLikePwd;
private String notLeftLikePwd;
private String notRightLikePwd;
private String equalAndLessThanPwd;
private String equalAndGreaterThanPwd;
private String lessThanPwd;
private String greaterThanPwd;
private Boolean pictureIdIsNull;
private String pictureIdNotEqual;
private List<String> inPictureId;
private List<String> notInPictureId;
private String likePictureId;
private String leftLikePictureId;
private String rightLikePictureId;
private String notLikePictureId;
private String notLeftLikePictureId;
private String notRightLikePictureId;
private String equalAndLessThanPictureId;
private String equalAndGreaterThanPictureId;
private String lessThanPictureId;
private String greaterThanPictureId;
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
public void inFirstList(List<String> list) {
       this.inFirst = list;
 }
public void notInFirstList(List<String> list) {
       this.notInFirst = list;
 }
public void addInFirst(String first) {
		if (inFirst ==null) {
			inFirst = new ArrayList<String>();
		}
		inFirst.add(first);
	}
public void addNotInFirst(String first) {
		if (notInFirst ==null) {
			notInFirst = new ArrayList<String>();
		}
		notInFirst.add(first);
	}
public void inLastList(List<String> list) {
       this.inLast = list;
 }
public void notInLastList(List<String> list) {
       this.notInLast = list;
 }
public void addInLast(String last) {
		if (inLast ==null) {
			inLast = new ArrayList<String>();
		}
		inLast.add(last);
	}
public void addNotInLast(String last) {
		if (notInLast ==null) {
			notInLast = new ArrayList<String>();
		}
		notInLast.add(last);
	}
public void inEmailList(List<String> list) {
       this.inEmail = list;
 }
public void notInEmailList(List<String> list) {
       this.notInEmail = list;
 }
public void addInEmail(String email) {
		if (inEmail ==null) {
			inEmail = new ArrayList<String>();
		}
		inEmail.add(email);
	}
public void addNotInEmail(String email) {
		if (notInEmail ==null) {
			notInEmail = new ArrayList<String>();
		}
		notInEmail.add(email);
	}
public void inPwdList(List<String> list) {
       this.inPwd = list;
 }
public void notInPwdList(List<String> list) {
       this.notInPwd = list;
 }
public void addInPwd(String pwd) {
		if (inPwd ==null) {
			inPwd = new ArrayList<String>();
		}
		inPwd.add(pwd);
	}
public void addNotInPwd(String pwd) {
		if (notInPwd ==null) {
			notInPwd = new ArrayList<String>();
		}
		notInPwd.add(pwd);
	}
public void inPictureIdList(List<String> list) {
       this.inPictureId = list;
 }
public void notInPictureIdList(List<String> list) {
       this.notInPictureId = list;
 }
public void addInPictureId(String pictureId) {
		if (inPictureId ==null) {
			inPictureId = new ArrayList<String>();
		}
		inPictureId.add(pictureId);
	}
public void addNotInPictureId(String pictureId) {
		if (notInPictureId ==null) {
			notInPictureId = new ArrayList<String>();
		}
		notInPictureId.add(pictureId);
	}
}

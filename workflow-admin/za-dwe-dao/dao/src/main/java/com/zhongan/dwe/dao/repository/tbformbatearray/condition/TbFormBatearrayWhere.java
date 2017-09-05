package com.zhongan.dwe.dao.repository.tbformbatearray.condition;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.zhongan.dwe.dao.dataobject.TbFormBatearray;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TbFormBatearrayWhere extends TbFormBatearray {
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
private Boolean codeIsNull;
private String codeNotEqual;
private List<String> inCode;
private List<String> notInCode;
private String likeCode;
private String leftLikeCode;
private String rightLikeCode;
private String notLikeCode;
private String notLeftLikeCode;
private String notRightLikeCode;
private String equalAndLessThanCode;
private String equalAndGreaterThanCode;
private String lessThanCode;
private String greaterThanCode;
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
private Boolean bytesIsNull;
private byte[] bytesNotEqual;
private List<byte[]> inBytes;
private List<byte[]> notInBytes;
private byte[] equalAndLessThanBytes;
private byte[] equalAndGreaterThanBytes;
private byte[] lessThanBytes;
private byte[] greaterThanBytes;
private Boolean gmtCreatedIsNull;
private Date gmtCreatedNotEqual;
private Date equalAndLessThanGmtCreated;
private Date equalAndGreaterThanGmtCreated;
private Date lessThanGmtCreated;
private Date greaterThanGmtCreated;
private Boolean gmtModifiedIsNull;
private Date gmtModifiedNotEqual;
private Date equalAndLessThanGmtModified;
private Date equalAndGreaterThanGmtModified;
private Date lessThanGmtModified;
private Date greaterThanGmtModified;
private Boolean creatorIsNull;
private String creatorNotEqual;
private List<String> inCreator;
private List<String> notInCreator;
private String likeCreator;
private String leftLikeCreator;
private String rightLikeCreator;
private String notLikeCreator;
private String notLeftLikeCreator;
private String notRightLikeCreator;
private String equalAndLessThanCreator;
private String equalAndGreaterThanCreator;
private String lessThanCreator;
private String greaterThanCreator;
private Boolean modifierIsNull;
private String modifierNotEqual;
private List<String> inModifier;
private List<String> notInModifier;
private String likeModifier;
private String leftLikeModifier;
private String rightLikeModifier;
private String notLikeModifier;
private String notLeftLikeModifier;
private String notRightLikeModifier;
private String equalAndLessThanModifier;
private String equalAndGreaterThanModifier;
private String lessThanModifier;
private String greaterThanModifier;
private Boolean isDeletedIsNull;
private String isDeletedNotEqual;
private List<String> inIsDeleted;
private List<String> notInIsDeleted;
private String likeIsDeleted;
private String leftLikeIsDeleted;
private String rightLikeIsDeleted;
private String notLikeIsDeleted;
private String notLeftLikeIsDeleted;
private String notRightLikeIsDeleted;
private String equalAndLessThanIsDeleted;
private String equalAndGreaterThanIsDeleted;
private String lessThanIsDeleted;
private String greaterThanIsDeleted;
private Boolean remarkIsNull;
private String remarkNotEqual;
private List<String> inRemark;
private List<String> notInRemark;
private String likeRemark;
private String leftLikeRemark;
private String rightLikeRemark;
private String notLikeRemark;
private String notLeftLikeRemark;
private String notRightLikeRemark;
private String equalAndLessThanRemark;
private String equalAndGreaterThanRemark;
private String lessThanRemark;
private String greaterThanRemark;
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
public void inCodeList(List<String> list) {
       this.inCode = list;
 }
public void notInCodeList(List<String> list) {
       this.notInCode = list;
 }
public void addInCode(String code) {
		if (inCode ==null) {
			inCode = new ArrayList<String>();
		}
		inCode.add(code);
	}
public void addNotInCode(String code) {
		if (notInCode ==null) {
			notInCode = new ArrayList<String>();
		}
		notInCode.add(code);
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
public void inBytesList(List<byte[]> list) {
       this.inBytes = list;
 }
public void notInBytesList(List<byte[]> list) {
       this.notInBytes = list;
 }
public void addInBytes(byte[] bytes) {
		if (inBytes ==null) {
			inBytes = new ArrayList<byte[]>();
		}
		inBytes.add(bytes);
	}
public void addNotInBytes(byte[] bytes) {
		if (notInBytes ==null) {
			notInBytes = new ArrayList<byte[]>();
		}
		notInBytes.add(bytes);
	}
public void inCreatorList(List<String> list) {
       this.inCreator = list;
 }
public void notInCreatorList(List<String> list) {
       this.notInCreator = list;
 }
public void addInCreator(String creator) {
		if (inCreator ==null) {
			inCreator = new ArrayList<String>();
		}
		inCreator.add(creator);
	}
public void addNotInCreator(String creator) {
		if (notInCreator ==null) {
			notInCreator = new ArrayList<String>();
		}
		notInCreator.add(creator);
	}
public void inModifierList(List<String> list) {
       this.inModifier = list;
 }
public void notInModifierList(List<String> list) {
       this.notInModifier = list;
 }
public void addInModifier(String modifier) {
		if (inModifier ==null) {
			inModifier = new ArrayList<String>();
		}
		inModifier.add(modifier);
	}
public void addNotInModifier(String modifier) {
		if (notInModifier ==null) {
			notInModifier = new ArrayList<String>();
		}
		notInModifier.add(modifier);
	}
public void inIsDeletedList(List<String> list) {
       this.inIsDeleted = list;
 }
public void notInIsDeletedList(List<String> list) {
       this.notInIsDeleted = list;
 }
public void addInIsDeleted(String isDeleted) {
		if (inIsDeleted ==null) {
			inIsDeleted = new ArrayList<String>();
		}
		inIsDeleted.add(isDeleted);
	}
public void addNotInIsDeleted(String isDeleted) {
		if (notInIsDeleted ==null) {
			notInIsDeleted = new ArrayList<String>();
		}
		notInIsDeleted.add(isDeleted);
	}
public void inRemarkList(List<String> list) {
       this.inRemark = list;
 }
public void notInRemarkList(List<String> list) {
       this.notInRemark = list;
 }
public void addInRemark(String remark) {
		if (inRemark ==null) {
			inRemark = new ArrayList<String>();
		}
		inRemark.add(remark);
	}
public void addNotInRemark(String remark) {
		if (notInRemark ==null) {
			notInRemark = new ArrayList<String>();
		}
		notInRemark.add(remark);
	}
}

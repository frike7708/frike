package com.zhongan.dwe.dao.repository.tbserverurl.condition;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.zhongan.dwe.dao.dataobject.TbServerUrl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TbServerUrlWhere extends TbServerUrl {
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
private Boolean ckeyIsNull;
private String ckeyNotEqual;
private List<String> inCkey;
private List<String> notInCkey;
private String likeCkey;
private String leftLikeCkey;
private String rightLikeCkey;
private String notLikeCkey;
private String notLeftLikeCkey;
private String notRightLikeCkey;
private String equalAndLessThanCkey;
private String equalAndGreaterThanCkey;
private String lessThanCkey;
private String greaterThanCkey;
private Boolean urlIsNull;
private String urlNotEqual;
private List<String> inUrl;
private List<String> notInUrl;
private String likeUrl;
private String leftLikeUrl;
private String rightLikeUrl;
private String notLikeUrl;
private String notLeftLikeUrl;
private String notRightLikeUrl;
private String equalAndLessThanUrl;
private String equalAndGreaterThanUrl;
private String lessThanUrl;
private String greaterThanUrl;
private Boolean versionIsNull;
private Integer versionNotEqual;
private List<Integer> inVersion;
private List<Integer> notInVersion;
private Integer equalAndLessThanVersion;
private Integer equalAndGreaterThanVersion;
private Integer lessThanVersion;
private Integer greaterThanVersion;
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
public void inCkeyList(List<String> list) {
       this.inCkey = list;
 }
public void notInCkeyList(List<String> list) {
       this.notInCkey = list;
 }
public void addInCkey(String ckey) {
		if (inCkey ==null) {
			inCkey = new ArrayList<String>();
		}
		inCkey.add(ckey);
	}
public void addNotInCkey(String ckey) {
		if (notInCkey ==null) {
			notInCkey = new ArrayList<String>();
		}
		notInCkey.add(ckey);
	}
public void inUrlList(List<String> list) {
       this.inUrl = list;
 }
public void notInUrlList(List<String> list) {
       this.notInUrl = list;
 }
public void addInUrl(String url) {
		if (inUrl ==null) {
			inUrl = new ArrayList<String>();
		}
		inUrl.add(url);
	}
public void addNotInUrl(String url) {
		if (notInUrl ==null) {
			notInUrl = new ArrayList<String>();
		}
		notInUrl.add(url);
	}
public void inVersionList(List<Integer> list) {
       this.inVersion = list;
 }
public void notInVersionList(List<Integer> list) {
       this.notInVersion = list;
 }
public void addInVersion(Integer version) {
		if (inVersion ==null) {
			inVersion = new ArrayList<Integer>();
		}
		inVersion.add(version);
	}
public void addNotInVersion(Integer version) {
		if (notInVersion ==null) {
			notInVersion = new ArrayList<Integer>();
		}
		notInVersion.add(version);
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
}

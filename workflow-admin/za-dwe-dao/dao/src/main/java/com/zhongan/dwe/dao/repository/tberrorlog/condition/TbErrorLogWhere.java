package com.zhongan.dwe.dao.repository.tberrorlog.condition;
import java.util.ArrayList;
import java.util.List;
import com.zhongan.dwe.dao.dataobject.TbErrorLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TbErrorLogWhere extends TbErrorLog {
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
private Boolean errorCodeIsNull;
private String errorCodeNotEqual;
private List<String> inErrorCode;
private List<String> notInErrorCode;
private String likeErrorCode;
private String leftLikeErrorCode;
private String rightLikeErrorCode;
private String notLikeErrorCode;
private String notLeftLikeErrorCode;
private String notRightLikeErrorCode;
private String equalAndLessThanErrorCode;
private String equalAndGreaterThanErrorCode;
private String lessThanErrorCode;
private String greaterThanErrorCode;
private Boolean errorTypeIsNull;
private String errorTypeNotEqual;
private List<String> inErrorType;
private List<String> notInErrorType;
private String likeErrorType;
private String leftLikeErrorType;
private String rightLikeErrorType;
private String notLikeErrorType;
private String notLeftLikeErrorType;
private String notRightLikeErrorType;
private String equalAndLessThanErrorType;
private String equalAndGreaterThanErrorType;
private String lessThanErrorType;
private String greaterThanErrorType;
private Boolean messageIsNull;
private String messageNotEqual;
private List<String> inMessage;
private List<String> notInMessage;
private String likeMessage;
private String leftLikeMessage;
private String rightLikeMessage;
private String notLikeMessage;
private String notLeftLikeMessage;
private String notRightLikeMessage;
private String equalAndLessThanMessage;
private String equalAndGreaterThanMessage;
private String lessThanMessage;
private String greaterThanMessage;
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
public void inErrorCodeList(List<String> list) {
       this.inErrorCode = list;
 }
public void notInErrorCodeList(List<String> list) {
       this.notInErrorCode = list;
 }
public void addInErrorCode(String errorCode) {
		if (inErrorCode ==null) {
			inErrorCode = new ArrayList<String>();
		}
		inErrorCode.add(errorCode);
	}
public void addNotInErrorCode(String errorCode) {
		if (notInErrorCode ==null) {
			notInErrorCode = new ArrayList<String>();
		}
		notInErrorCode.add(errorCode);
	}
public void inErrorTypeList(List<String> list) {
       this.inErrorType = list;
 }
public void notInErrorTypeList(List<String> list) {
       this.notInErrorType = list;
 }
public void addInErrorType(String errorType) {
		if (inErrorType ==null) {
			inErrorType = new ArrayList<String>();
		}
		inErrorType.add(errorType);
	}
public void addNotInErrorType(String errorType) {
		if (notInErrorType ==null) {
			notInErrorType = new ArrayList<String>();
		}
		notInErrorType.add(errorType);
	}
public void inMessageList(List<String> list) {
       this.inMessage = list;
 }
public void notInMessageList(List<String> list) {
       this.notInMessage = list;
 }
public void addInMessage(String message) {
		if (inMessage ==null) {
			inMessage = new ArrayList<String>();
		}
		inMessage.add(message);
	}
public void addNotInMessage(String message) {
		if (notInMessage ==null) {
			notInMessage = new ArrayList<String>();
		}
		notInMessage.add(message);
	}
}

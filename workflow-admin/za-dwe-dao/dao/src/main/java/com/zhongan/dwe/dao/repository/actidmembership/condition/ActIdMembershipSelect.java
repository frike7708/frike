package com.zhongan.dwe.dao.repository.actidmembership.condition;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class ActIdMembershipSelect {
public static final String userId = "USER_ID_ as userId";
public static final String groupId = "GROUP_ID_ as groupId";
 List<String> columns = new ArrayList<String>();

public ActIdMembershipSelect(List<String> columns){
	super();
	this.columns = columns;
}

public ActIdMembershipSelect(){
	super();
}

public ActIdMembershipSelect addColumn(String column) {
	columns.add(column);
	return this;
}
}

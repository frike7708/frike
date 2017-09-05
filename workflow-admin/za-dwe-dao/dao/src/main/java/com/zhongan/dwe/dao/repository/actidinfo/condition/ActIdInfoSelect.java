package com.zhongan.dwe.dao.repository.actidinfo.condition;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class ActIdInfoSelect {
public static final String id = "ID_ as id";
public static final String rev = "REV_ as rev";
public static final String userId = "USER_ID_ as userId";
public static final String type = "TYPE_ as type";
public static final String key = "KEY_ as key";
public static final String value = "VALUE_ as value";
public static final String password = "PASSWORD_ as password";
public static final String parentId = "PARENT_ID_ as parentId";
 List<String> columns = new ArrayList<String>();

public ActIdInfoSelect(List<String> columns){
	super();
	this.columns = columns;
}

public ActIdInfoSelect(){
	super();
}

public ActIdInfoSelect addColumn(String column) {
	columns.add(column);
	return this;
}
}

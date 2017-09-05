package com.zhongan.dwe.dao.repository.tbserverurl.condition;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class TbServerUrlSelect {
public static final String id = "ID as id";
public static final String ckey = "CKEY as ckey";
public static final String url = "URL as url";
public static final String version = "VERSION as version";
public static final String gmtCreated = "GMT_CREATED as gmtCreated";
public static final String gmtModified = "GMT_MODIFIED as gmtModified";
public static final String creator = "CREATOR as creator";
public static final String modifier = "MODIFIER as modifier";
public static final String isDeleted = "IS_DELETED as isDeleted";
 List<String> columns = new ArrayList<String>();

public TbServerUrlSelect(List<String> columns){
	super();
	this.columns = columns;
}

public TbServerUrlSelect(){
	super();
}

public TbServerUrlSelect addColumn(String column) {
	columns.add(column);
	return this;
}
}

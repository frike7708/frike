package com.zhongan.dwe.dao.repository.actidgroup.condition;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class ActIdGroupSelect {
public static final String id = "ID_ as id";
public static final String rev = "REV_ as rev";
public static final String name = "NAME_ as name";
public static final String type = "TYPE_ as type";
 List<String> columns = new ArrayList<String>();

public ActIdGroupSelect(List<String> columns){
	super();
	this.columns = columns;
}

public ActIdGroupSelect(){
	super();
}

public ActIdGroupSelect addColumn(String column) {
	columns.add(column);
	return this;
}
}

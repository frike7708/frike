package com.zhongan.dwe.dao.repository.actiduser.condition;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class ActIdUserSelect {
public static final String id = "ID_ as id";
public static final String rev = "REV_ as rev";
public static final String first = "FIRST_ as first";
public static final String last = "LAST_ as last";
public static final String email = "EMAIL_ as email";
public static final String pwd = "PWD_ as pwd";
public static final String pictureId = "PICTURE_ID_ as pictureId";
 List<String> columns = new ArrayList<String>();

public ActIdUserSelect(List<String> columns){
	super();
	this.columns = columns;
}

public ActIdUserSelect(){
	super();
}

public ActIdUserSelect addColumn(String column) {
	columns.add(column);
	return this;
}
}

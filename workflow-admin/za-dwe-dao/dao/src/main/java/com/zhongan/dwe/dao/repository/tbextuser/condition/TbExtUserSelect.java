package com.zhongan.dwe.dao.repository.tbextuser.condition;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class TbExtUserSelect {
public static final String id = "ID as id";
public static final String rev = "REV as rev";
public static final String first = "FIRST as first";
public static final String last = "LAST as last";
public static final String email = "EMAIL as email";
public static final String pwd = "PWD as pwd";
 List<String> columns = new ArrayList<String>();

public TbExtUserSelect(List<String> columns){
	super();
	this.columns = columns;
}

public TbExtUserSelect(){
	super();
}

public TbExtUserSelect addColumn(String column) {
	columns.add(column);
	return this;
}
}

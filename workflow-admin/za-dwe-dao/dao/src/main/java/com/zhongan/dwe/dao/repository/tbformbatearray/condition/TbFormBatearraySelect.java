package com.zhongan.dwe.dao.repository.tbformbatearray.condition;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class TbFormBatearraySelect {
public static final String id = "ID as id";
public static final String code = "CODE as code";
public static final String name = "NAME as name";
public static final String bytes = "BYTES as bytes";
public static final String gmtCreated = "GMT_CREATED as gmtCreated";
public static final String gmtModified = "GMT_MODIFIED as gmtModified";
public static final String creator = "CREATOR as creator";
public static final String modifier = "MODIFIER as modifier";
public static final String isDeleted = "IS_DELETED as isDeleted";
public static final String remark = "REMARK as remark";
 List<String> columns = new ArrayList<String>();

public TbFormBatearraySelect(List<String> columns){
	super();
	this.columns = columns;
}

public TbFormBatearraySelect(){
	super();
}

public TbFormBatearraySelect addColumn(String column) {
	columns.add(column);
	return this;
}
}

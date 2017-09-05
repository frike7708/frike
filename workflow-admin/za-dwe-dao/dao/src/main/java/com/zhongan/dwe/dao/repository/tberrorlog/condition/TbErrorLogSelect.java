package com.zhongan.dwe.dao.repository.tberrorlog.condition;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class TbErrorLogSelect {
public static final String id = "ID as id";
public static final String errorCode = "ERROR_CODE as errorCode";
public static final String errorType = "ERROR_TYPE as errorType";
public static final String message = "MESSAGE as message";
 List<String> columns = new ArrayList<String>();

public TbErrorLogSelect(List<String> columns){
	super();
	this.columns = columns;
}

public TbErrorLogSelect(){
	super();
}

public TbErrorLogSelect addColumn(String column) {
	columns.add(column);
	return this;
}
}

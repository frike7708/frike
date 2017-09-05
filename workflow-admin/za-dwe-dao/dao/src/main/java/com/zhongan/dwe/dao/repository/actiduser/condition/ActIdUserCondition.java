package com.zhongan.dwe.dao.repository.actiduser.condition;
import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageInfo;

import lombok.Data;

@Data
public class ActIdUserCondition {
	
	ActIdUserSelect select;
	
	ActIdUserWhere where;
	
	PageInfo page;
	
	OrderByInfo orderBy;
	
	public ActIdUserCondition(ActIdUserSelect select, ActIdUserWhere where, PageInfo page, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.page = page;
		this.orderBy = orderBy;
	}

	public ActIdUserCondition() {
		super();
	}

	public ActIdUserCondition(ActIdUserSelect select) {
		super();
		this.select = select;
	}

	public ActIdUserCondition(ActIdUserWhere where) {
		super();
		this.where = where;
	}

	public ActIdUserCondition(ActIdUserSelect select, ActIdUserWhere where, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.orderBy = orderBy;
	}

	public ActIdUserCondition(ActIdUserSelect select,ActIdUserWhere where) {
		super();
		this.select = select;
		this.where = where;
	}
	

}
package com.zhongan.dwe.dao.repository.actidgroup.condition;
import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageInfo;

import lombok.Data;

@Data
public class ActIdGroupCondition {
	
	ActIdGroupSelect select;
	
	ActIdGroupWhere where;
	
	PageInfo page;
	
	OrderByInfo orderBy;
	
	public ActIdGroupCondition(ActIdGroupSelect select, ActIdGroupWhere where, PageInfo page, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.page = page;
		this.orderBy = orderBy;
	}

	public ActIdGroupCondition() {
		super();
	}

	public ActIdGroupCondition(ActIdGroupSelect select) {
		super();
		this.select = select;
	}

	public ActIdGroupCondition(ActIdGroupWhere where) {
		super();
		this.where = where;
	}

	public ActIdGroupCondition(ActIdGroupSelect select, ActIdGroupWhere where, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.orderBy = orderBy;
	}

	public ActIdGroupCondition(ActIdGroupSelect select,ActIdGroupWhere where) {
		super();
		this.select = select;
		this.where = where;
	}
	

}
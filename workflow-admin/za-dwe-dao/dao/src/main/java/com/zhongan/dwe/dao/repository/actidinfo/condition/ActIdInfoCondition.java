package com.zhongan.dwe.dao.repository.actidinfo.condition;
import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageInfo;

import lombok.Data;

@Data
public class ActIdInfoCondition {
	
	ActIdInfoSelect select;
	
	ActIdInfoWhere where;
	
	PageInfo page;
	
	OrderByInfo orderBy;
	
	public ActIdInfoCondition(ActIdInfoSelect select, ActIdInfoWhere where, PageInfo page, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.page = page;
		this.orderBy = orderBy;
	}

	public ActIdInfoCondition() {
		super();
	}

	public ActIdInfoCondition(ActIdInfoSelect select) {
		super();
		this.select = select;
	}

	public ActIdInfoCondition(ActIdInfoWhere where) {
		super();
		this.where = where;
	}

	public ActIdInfoCondition(ActIdInfoSelect select, ActIdInfoWhere where, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.orderBy = orderBy;
	}

	public ActIdInfoCondition(ActIdInfoSelect select,ActIdInfoWhere where) {
		super();
		this.select = select;
		this.where = where;
	}
	

}
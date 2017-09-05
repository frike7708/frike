package com.zhongan.dwe.dao.repository.actidmembership.condition;
import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageInfo;

import lombok.Data;

@Data
public class ActIdMembershipCondition {
	
	ActIdMembershipSelect select;
	
	ActIdMembershipWhere where;
	
	PageInfo page;
	
	OrderByInfo orderBy;
	
	public ActIdMembershipCondition(ActIdMembershipSelect select, ActIdMembershipWhere where, PageInfo page, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.page = page;
		this.orderBy = orderBy;
	}

	public ActIdMembershipCondition() {
		super();
	}

	public ActIdMembershipCondition(ActIdMembershipSelect select) {
		super();
		this.select = select;
	}

	public ActIdMembershipCondition(ActIdMembershipWhere where) {
		super();
		this.where = where;
	}

	public ActIdMembershipCondition(ActIdMembershipSelect select, ActIdMembershipWhere where, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.orderBy = orderBy;
	}

	public ActIdMembershipCondition(ActIdMembershipSelect select,ActIdMembershipWhere where) {
		super();
		this.select = select;
		this.where = where;
	}
	

}
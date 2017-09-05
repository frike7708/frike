package com.zhongan.dwe.dao.repository.tbserverurl.condition;
import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageInfo;

import lombok.Data;

@Data
public class TbServerUrlCondition {
	
	TbServerUrlSelect select;
	
	TbServerUrlWhere where;
	
	PageInfo page;
	
	OrderByInfo orderBy;
	
	public TbServerUrlCondition(TbServerUrlSelect select, TbServerUrlWhere where, PageInfo page, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.page = page;
		this.orderBy = orderBy;
	}

	public TbServerUrlCondition() {
		super();
	}

	public TbServerUrlCondition(TbServerUrlSelect select) {
		super();
		this.select = select;
	}

	public TbServerUrlCondition(TbServerUrlWhere where) {
		super();
		this.where = where;
	}

	public TbServerUrlCondition(TbServerUrlSelect select, TbServerUrlWhere where, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.orderBy = orderBy;
	}

	public TbServerUrlCondition(TbServerUrlSelect select,TbServerUrlWhere where) {
		super();
		this.select = select;
		this.where = where;
	}
	

}
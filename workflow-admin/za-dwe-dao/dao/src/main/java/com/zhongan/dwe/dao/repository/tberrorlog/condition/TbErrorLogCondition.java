package com.zhongan.dwe.dao.repository.tberrorlog.condition;
import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageInfo;

import lombok.Data;

@Data
public class TbErrorLogCondition {
	
	TbErrorLogSelect select;
	
	TbErrorLogWhere where;
	
	PageInfo page;
	
	OrderByInfo orderBy;
	
	public TbErrorLogCondition(TbErrorLogSelect select, TbErrorLogWhere where, PageInfo page, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.page = page;
		this.orderBy = orderBy;
	}

	public TbErrorLogCondition() {
		super();
	}

	public TbErrorLogCondition(TbErrorLogSelect select) {
		super();
		this.select = select;
	}

	public TbErrorLogCondition(TbErrorLogWhere where) {
		super();
		this.where = where;
	}

	public TbErrorLogCondition(TbErrorLogSelect select, TbErrorLogWhere where, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.orderBy = orderBy;
	}

	public TbErrorLogCondition(TbErrorLogSelect select,TbErrorLogWhere where) {
		super();
		this.select = select;
		this.where = where;
	}
	

}
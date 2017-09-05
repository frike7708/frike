package com.zhongan.dwe.dao.repository.tbextuser.condition;
import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageInfo;

import lombok.Data;

@Data
public class TbExtUserCondition {
	
	TbExtUserSelect select;
	
	TbExtUserWhere where;
	
	PageInfo page;
	
	OrderByInfo orderBy;
	
	public TbExtUserCondition(TbExtUserSelect select, TbExtUserWhere where, PageInfo page, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.page = page;
		this.orderBy = orderBy;
	}

	public TbExtUserCondition() {
		super();
	}

	public TbExtUserCondition(TbExtUserSelect select) {
		super();
		this.select = select;
	}

	public TbExtUserCondition(TbExtUserWhere where) {
		super();
		this.where = where;
	}

	public TbExtUserCondition(TbExtUserSelect select, TbExtUserWhere where, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.orderBy = orderBy;
	}

	public TbExtUserCondition(TbExtUserSelect select,TbExtUserWhere where) {
		super();
		this.select = select;
		this.where = where;
	}
	

}
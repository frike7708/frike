package com.zhongan.dwe.dao.repository.tbformbatearray.condition;
import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageInfo;

import lombok.Data;

@Data
public class TbFormBatearrayCondition {
	
	TbFormBatearraySelect select;
	
	TbFormBatearrayWhere where;
	
	PageInfo page;
	
	OrderByInfo orderBy;
	
	public TbFormBatearrayCondition(TbFormBatearraySelect select, TbFormBatearrayWhere where, PageInfo page, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.page = page;
		this.orderBy = orderBy;
	}

	public TbFormBatearrayCondition() {
		super();
	}

	public TbFormBatearrayCondition(TbFormBatearraySelect select) {
		super();
		this.select = select;
	}

	public TbFormBatearrayCondition(TbFormBatearrayWhere where) {
		super();
		this.where = where;
	}

	public TbFormBatearrayCondition(TbFormBatearraySelect select, TbFormBatearrayWhere where, OrderByInfo orderBy) {
		super();
		this.select = select;
		this.where = where;
		this.orderBy = orderBy;
	}

	public TbFormBatearrayCondition(TbFormBatearraySelect select,TbFormBatearrayWhere where) {
		super();
		this.select = select;
		this.where = where;
	}
	

}
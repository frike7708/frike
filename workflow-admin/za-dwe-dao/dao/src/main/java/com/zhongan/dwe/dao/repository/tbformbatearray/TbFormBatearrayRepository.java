package com.zhongan.dwe.dao.repository.tbformbatearray;

import com.zhongan.components.mybatis.base.repository.BaseRepository;
import com.zhongan.dwe.dao.dataobject.TbFormBatearray;
import com.zhongan.dwe.dao.repository.tbformbatearray.condition.TbFormBatearrayWhere;
import com.zhongan.dwe.dao.repository.tbformbatearray.condition.TbFormBatearrayCondition;

public interface TbFormBatearrayRepository extends BaseRepository<TbFormBatearray,TbFormBatearrayCondition,TbFormBatearrayWhere>{


	public void insertTbFormbate(TbFormBatearray  tbFormBatearray);
}

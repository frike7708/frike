package com.zhongan.dwe.dao.repository.tberrorlog;

import com.zhongan.components.mybatis.base.repository.BaseRepository;
import com.zhongan.dwe.dao.dataobject.TbErrorLog;
import com.zhongan.dwe.dao.repository.tberrorlog.condition.TbErrorLogWhere;
import com.zhongan.dwe.dao.repository.tberrorlog.condition.TbErrorLogCondition;

public interface TbErrorLogRepository extends BaseRepository<TbErrorLog,TbErrorLogCondition,TbErrorLogWhere>{


}

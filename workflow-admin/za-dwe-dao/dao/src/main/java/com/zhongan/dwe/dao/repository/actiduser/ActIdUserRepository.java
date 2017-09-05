package com.zhongan.dwe.dao.repository.actiduser;

import com.zhongan.components.mybatis.base.repository.BaseRepository;
import com.zhongan.dwe.dao.dataobject.ActIdUser;
import com.zhongan.dwe.dao.repository.actiduser.condition.ActIdUserWhere;
import com.zhongan.dwe.dao.repository.actiduser.condition.ActIdUserCondition;

public interface ActIdUserRepository extends BaseRepository<ActIdUser,ActIdUserCondition,ActIdUserWhere>{


}

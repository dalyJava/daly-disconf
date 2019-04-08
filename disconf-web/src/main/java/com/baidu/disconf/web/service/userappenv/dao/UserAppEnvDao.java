package com.baidu.disconf.web.service.userappenv.dao;

import com.baidu.disconf.web.service.userappenv.bo.UserAppEnv;
import com.baidu.unbiz.common.genericdao.dao.BaseDao;
import java.util.Set;

public interface UserAppEnvDao extends BaseDao<Long, UserAppEnv> {
    Set<Long> getAppIds(Long userId);
}

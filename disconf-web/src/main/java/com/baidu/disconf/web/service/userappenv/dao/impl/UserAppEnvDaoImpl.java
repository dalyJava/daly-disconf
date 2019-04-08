package com.baidu.disconf.web.service.userappenv.dao.impl;

import com.baidu.disconf.web.service.userappenv.bo.UserAppEnv;
import com.baidu.disconf.web.service.userappenv.dao.UserAppEnvDao;
import com.baidu.dsp.common.dao.AbstractDao;
import com.baidu.dsp.common.dao.Columns;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public class UserAppEnvDaoImpl extends AbstractDao<Long, UserAppEnv> implements UserAppEnvDao {


  @Override
  public Set<Long> getAppIds(Long userId) {
    List<UserAppEnv> userAppEnvs = find(Columns.USER_ID, userId);
    Set<Long> appIds  = new HashSet<>();
    userAppEnvs.stream().forEach(userAppEnv->appIds.add(userAppEnv.getAppId()));
    return appIds;
  }
}

package com.baidu.disconf.web.service.userappenv.service.impl;

import com.baidu.disconf.web.service.app.bo.App;
import com.baidu.disconf.web.service.app.dao.AppDao;
import com.baidu.disconf.web.service.app.vo.AppListVo;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.userappenv.dao.UserAppEnvDao;
import com.baidu.disconf.web.service.userappenv.service.UserAppEnvMgr;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserAppEnvMgrImpl implements UserAppEnvMgr {

  @Autowired
  private UserAppEnvDao userAppEnvDao;
  @Autowired
  private AppDao appDao;

  @Override
  public Set<AppListVo> getAppId(Long userId) {
    Set<Long> appIds = userAppEnvDao.getAppIds(userId);
    List<App> apps = appDao.getByIds(appIds);
    Set<AppListVo> appsSet = new HashSet<>();
    apps.stream().forEach(app -> appsSet.add(new AppListVo(app.getId(),app.getName())));
    return appsSet;
  }
}

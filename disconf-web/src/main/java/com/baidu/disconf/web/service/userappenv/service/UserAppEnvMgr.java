package com.baidu.disconf.web.service.userappenv.service;

import com.baidu.disconf.web.service.app.vo.AppListVo;
import com.baidu.disconf.web.service.user.bo.User;
import java.util.Set;

public interface UserAppEnvMgr {
  
  Set<AppListVo> getAppId(Long userId);
}

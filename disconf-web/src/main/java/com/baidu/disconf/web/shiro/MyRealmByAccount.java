package com.baidu.disconf.web.shiro;

import com.baidu.disconf.web.service.app.vo.AppListVo;
import com.baidu.disconf.web.service.role.bo.Role;
import com.baidu.disconf.web.service.role.service.RoleMgr;
import com.baidu.disconf.web.service.sign.type.SigninTypeEnum;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.userappenv.service.UserAppEnvMgr;
import java.util.List;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyRealmByAccount extends BaseRealm {
  @Autowired
  private RoleMgr roleMgr;

  @Autowired
  private UserAppEnvMgr userAppEnvMgr;

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;//获取用户输入的token
    String username = String.valueOf(userToken.getUsername());
    String password = String.valueOf(userToken.getPassword());
    User currentUser = authValidator.validateLogin(username, password, SigninTypeEnum.ACCOUNT);
    //将role放进去
    List<Role> roleList = roleMgr.findByUserId(currentUser.getId());
    currentUser.setRoleList(roleList);
    //将app放进去
    Set<AppListVo> appListVoSet = userAppEnvMgr.getAppId(currentUser.getId());
    currentUser.setAppSet(appListVoSet);
    return new SimpleAuthenticationInfo(currentUser, userToken.getCredentials(), getName());
  }

}

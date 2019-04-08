package com.baidu.disconf.web.shiro;

import com.baidu.disconf.web.service.sign.type.SigninTypeEnum;
import com.baidu.disconf.web.service.user.bo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

@Service
public class MyRealmByEmail extends BaseRealm {

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;//获取用户输入的token
    String username = String.valueOf(userToken.getUsername());
    String password = String.valueOf(userToken.getPassword());
    User currentUser = authValidator.validateLogin(username, password, SigninTypeEnum.EMAIL);
    return new SimpleAuthenticationInfo(currentUser, userToken.getCredentials(), getName());
  }
}

package com.baidu.disconf.web.shiro;

import com.baidu.disconf.web.service.sign.service.SignMgr;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.web.auth.validator.AuthValidator;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseRealm extends AuthorizingRealm {

  @Autowired
  protected AuthValidator authValidator;
  @Autowired
  private SignMgr signMgr;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
    //因为认证时放的是user
    User currentUser = (User) principalCollection.getPrimaryPrincipal();
    Set<String> rolesName = signMgr.getRolesName(currentUser.getName());
    authorizationInfo.setRoles(rolesName);
    authorizationInfo.setStringPermissions(signMgr.getPermissions(currentUser.getName()));
    return authorizationInfo;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    return null;
  }


}

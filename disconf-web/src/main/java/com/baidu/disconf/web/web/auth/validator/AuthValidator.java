package com.baidu.disconf.web.web.auth.validator;

import com.baidu.disconf.web.service.sign.type.SigninTypeEnum;
import com.baidu.disconf.web.utils.ShiroUtils;
import org.apache.ibatis.jdbc.Null;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.disconf.web.service.sign.service.SignMgr;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.form.PasswordModifyForm;
import com.baidu.disconf.web.service.user.service.UserMgr;
import com.baidu.dsp.common.exception.FieldException;
import com.baidu.ub.common.commons.ThreadContext;

/**
 * 权限验证
 *
 * @author liaoqiqi
 * @version 2014-7-2
 */
@Component
public class AuthValidator {

  @Autowired
  private SignMgr signMgr;

  @Autowired
  private UserMgr userMgr;

  /**
   * 改写源码 验证登录 根据SigninTypeEnum 类型，选择相应的验证方式
   */
  public User validateLogin(String principal, String credentials, SigninTypeEnum signinTypeEnum) {

    User loginUser;

    // 校验用户是否存在
    if (SigninTypeEnum.MOBILE_PHONE.getType() == signinTypeEnum.getType()) {
      loginUser = signMgr.getUserByMobilePhone(principal);
    } else if (SigninTypeEnum.EMAIL.getType() == signinTypeEnum.getType()) {
      loginUser = signMgr.getUserByEmail(principal);
    } else {
      loginUser = signMgr.getUserByName(principal);
    }
    if (loginUser == null) {
      throw new FieldException(principal, "user.not.exist", new UnknownAccountException());
    }

    // 校验密码
    if (!signMgr.validate(loginUser.getPassword(), credentials)) {
      throw new FieldException(credentials, "password.not.right",
          new UnknownAccountException());
    }
    return loginUser;
  }

  /**
   * 验证密码更新
   */
  public void validatePasswordModify(PasswordModifyForm passwordModifyForm) {

    User currentUser = ShiroUtils.getCurrentUser();
    User user = userMgr.getUser(currentUser.getId());

    // 校验密码
    if (!signMgr.validate(user.getPassword(), passwordModifyForm.getOld_password())) {
      throw new FieldException(PasswordModifyForm.OLD_PASSWORD, "old.password.not.right", null);
    }

    if (!passwordModifyForm.getNew_password().equals(passwordModifyForm.getNew_password_2())) {
      throw new FieldException(PasswordModifyForm.NEW_PASSWORD, "two.password.not.equal", null);
    }
  }
}

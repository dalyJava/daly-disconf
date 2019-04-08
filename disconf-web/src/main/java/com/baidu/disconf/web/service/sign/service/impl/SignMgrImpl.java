package com.baidu.disconf.web.service.sign.service.impl;

import com.baidu.disconf.web.service.role.bo.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.disconf.web.service.sign.service.SignMgr;
import com.baidu.disconf.web.service.sign.utils.SignUtils;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.dao.UserDao;

/**
 * 与登录登出相关的
 *
 * @author liaoqiqi
 * @version 2014-2-6
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SignMgrImpl implements SignMgr {

  protected static final Logger LOG = LoggerFactory.getLogger(SignMgrImpl.class);

  @Autowired
  private UserDao userDao;

  /**
   * 根据 用户名获取用户
   */
  @Override
  public User getUserByName(String username) {

    return userDao.getUserByUserName(username);
  }

  @Override
  public User getUserByEmail(String email) {
    return userDao.getUserByEmail(email);
  }

  @Override
  public User getUserByMobilePhone(String mobilePhone) {
    return userDao.getUserByMobilePhone(mobilePhone);
  }

  /**
   * 验证密码是否正确
   */
  public boolean validate(String userPassword, String passwordToBeValidate) {

    //String data = SignUtils.createPassword(passwordToBeValidate);
    //return data.equals(userPassword);
    return passwordToBeValidate.equals(userPassword);
  }

  @Override
  public Set<Role> getRolesList(String username) {
    List<Role> roleList = userDao.getRolesByName(username);
    return new HashSet<>(roleList);
  }

  @Override
  public Set<String> getRolesName(String username) {
    List<Role> rolesList = userDao.getRolesByName(username);
    Set<String> roleNameSet = new HashSet<>();
    if (!rolesList.isEmpty()) {
      rolesList.forEach(role -> roleNameSet.add(role.getRoleName()));
    }
    return roleNameSet;
  }

  @Override
  public Set<String> getPermissions(String username) {
    Set<Role> rolesList = getRolesList(username);
    Set<String> permissionsSet = new HashSet<>();
    rolesList.forEach(role -> permissionsSet.addAll(role.getPermissions()));
    return permissionsSet;
  }


}

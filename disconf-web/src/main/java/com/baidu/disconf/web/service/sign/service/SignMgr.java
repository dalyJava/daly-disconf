package com.baidu.disconf.web.service.sign.service;

import com.baidu.disconf.web.service.role.bo.Role;
import com.baidu.disconf.web.service.user.bo.User;
import java.util.List;
import java.util.Set;

/**
 * @author liaoqiqi
 * @version 2014-2-6
 */
public interface SignMgr {

  User getUserByName(String name);

  User getUserByEmail(String email);

  User getUserByMobilePhone(String mobilePhone);

  boolean validate(String userPassword, String passwordToBeValidate);

  Set<Role> getRolesList(String username);

  Set<String> getRolesName(String username);

  Set<String> getPermissions(String username);

}

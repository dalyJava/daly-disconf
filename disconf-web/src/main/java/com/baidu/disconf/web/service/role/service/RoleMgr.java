package com.baidu.disconf.web.service.role.service;

import java.util.List;

import com.baidu.disconf.web.service.role.bo.Role;

/**
 * @author weiwei
 * @date 2013-12-24
 */
public interface RoleMgr {

  Role get(Long roleId);

  List<Role> findAll();

  List<Role> findByUserId(Long userId);


}

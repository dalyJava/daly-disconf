package com.baidu.disconf.web.service.roleuser.dao;

import com.baidu.disconf.web.service.roleuser.bo.RoleUser;
import com.baidu.unbiz.common.genericdao.dao.BaseDao;
import java.util.List;

public interface RoleUserDao extends BaseDao<Long, RoleUser> {
  List<Long> findRoleIds(Long userId);
}

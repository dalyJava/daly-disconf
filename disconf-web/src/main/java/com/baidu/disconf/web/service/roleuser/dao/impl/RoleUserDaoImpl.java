package com.baidu.disconf.web.service.roleuser.dao.impl;

import com.baidu.disconf.web.service.roleuser.bo.RoleUser;
import com.baidu.disconf.web.service.roleuser.dao.RoleUserDao;
import com.baidu.dsp.common.dao.AbstractDao;
import com.baidu.dsp.common.dao.Columns;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RoleUserDaoImpl extends AbstractDao<Long, RoleUser> implements RoleUserDao {


  @Override
  public List<Long> findRoleIds(Long userId) {
    List<RoleUser> roleUsers = find(Columns.USER_ID, userId);
    List<Long> roleIds = new ArrayList<>();
    roleUsers.stream().forEach(roleUser->roleIds.add(roleUser.getRoleId()));
    return roleIds;
  }


}

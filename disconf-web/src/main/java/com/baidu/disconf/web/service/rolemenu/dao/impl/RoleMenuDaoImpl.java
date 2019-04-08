package com.baidu.disconf.web.service.rolemenu.dao.impl;

import com.baidu.disconf.web.service.rolemenu.bo.RoleMenu;
import com.baidu.disconf.web.service.rolemenu.dao.RoleMenuDao;
import com.baidu.dsp.common.dao.AbstractDao;
import com.baidu.dsp.common.dao.Columns;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RoleMenuDaoImpl extends AbstractDao<Long, RoleMenu> implements RoleMenuDao {

  @Override
  public List<Long> getMenuIds(Long roleId) {
    List<RoleMenu> roleMenus = find(Columns.ROLE_ID, roleId);
    List<Long> menuIds = new ArrayList<>();
    roleMenus.stream().forEach(roleMenu -> menuIds.add(roleMenu.getMenuId()));
    return menuIds;
  }
}

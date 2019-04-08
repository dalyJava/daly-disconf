package com.baidu.disconf.web.service.rolemenu.dao;

import com.baidu.disconf.web.service.rolemenu.bo.RoleMenu;
import com.baidu.unbiz.common.genericdao.dao.BaseDao;
import java.util.List;

public interface RoleMenuDao extends BaseDao<Long, RoleMenu> {
    List<Long> getMenuIds(Long roleId);
}

package com.baidu.disconf.web.service.menu.dao;

import com.baidu.disconf.web.service.menu.bo.Menu;
import com.baidu.unbiz.common.genericdao.dao.BaseDao;
import java.util.List;

public interface MenuDao extends BaseDao<Long, Menu> {

  List<Menu> getMenuFilter(String column,String value);

}

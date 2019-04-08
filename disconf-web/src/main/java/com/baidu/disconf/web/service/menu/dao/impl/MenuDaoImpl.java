package com.baidu.disconf.web.service.menu.dao.impl;

import com.baidu.disconf.web.service.menu.bo.Menu;
import com.baidu.disconf.web.service.menu.dao.MenuDao;
import com.baidu.dsp.common.dao.AbstractDao;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class MenuDaoImpl extends AbstractDao<Long, Menu> implements MenuDao {


  @Override
  public List<Menu> getMenuFilter(String column, String value) {
    List<Menu> menus = find(column, value);
    return menus;
  }
}

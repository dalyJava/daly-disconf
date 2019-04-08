package com.baidu.disconf.web.service.menu.service;

import com.baidu.disconf.web.service.menu.bo.Menu;
import java.util.List;

public interface MenuService{

  Menu getMenuTree(List<Long> roleIds);

}

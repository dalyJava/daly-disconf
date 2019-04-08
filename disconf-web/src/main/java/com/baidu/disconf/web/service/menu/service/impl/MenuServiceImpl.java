package com.baidu.disconf.web.service.menu.service.impl;

import com.baidu.disconf.web.service.menu.bo.Menu;
import com.baidu.disconf.web.service.menu.constant.MenuConstant;
import com.baidu.disconf.web.service.menu.dao.MenuDao;
import com.baidu.disconf.web.service.menu.service.MenuService;
import com.baidu.disconf.web.service.rolemenu.dao.RoleMenuDao;
import com.baidu.disconf.web.utils.ShiroUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

  @Autowired
  private MenuDao menuDao;

  @Autowired
  private RoleMenuDao roleMenuDao;

  @Override
  public Menu getMenuTree(List<Long> roleIds) {
    if(roleIds == null){
      roleIds = ShiroUtils.getCurrentUserRoleIds();
    }
    List<Menu> menuAllList = getMenuByRoleIds(roleIds);
    log.info("menuAllList.size()::{}", menuAllList.size());
    Menu menuRoot = null;
    for (Menu menu : menuAllList) {
      if (menu.getParentId() == MenuConstant.MENU_ROOT_ID) {
        menuRoot = menu;
        menuAllList.remove(menu);//提升性能
        break;
      }
    }
    List<Menu> childList = new ArrayList<>();
    for (Menu menu : menuAllList) {
      if (menu.getParentId()==(menuRoot.getId())) {
        childList.add(menu);
      }
      for (Menu children : menuAllList) {
        if (menu.getId() == children.getParentId()) {
          if (menu.getChild() == null) {
            menu.setChild(new ArrayList<>());
          }
          menu.getChild().add(children);
        }
      }
    }
    if (childList.size() > 0) {
      menuRoot.setChild(childList);
    }
    return menuRoot;
  }


  private List<Menu> getMenuByRoleIds(List<Long> roleIds){
    Set<Long> menuIdsList = new HashSet<>();
    roleIds.stream().forEach(roleId ->{
      List<Long> menuIds = roleMenuDao.getMenuIds(roleId);
      menuIdsList.addAll(menuIds);
    });
    List<Menu> roleMenuList = new ArrayList<>();
    menuIdsList.stream().forEach(menuId->{
      Menu menu = menuDao.get(menuId);
      roleMenuList.add(menu);
    });
    //需要去重复
    return roleMenuList;
  }

}

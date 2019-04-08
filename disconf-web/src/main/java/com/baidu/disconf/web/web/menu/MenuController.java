package com.baidu.disconf.web.web.menu;

import com.baidu.disconf.web.service.menu.bo.Menu;
import com.baidu.disconf.web.service.menu.service.MenuService;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(WebConstants.API_PREFIX + "/menu")
public class MenuController extends BaseController {

  @Autowired
  private MenuService menuService;

  /**
   * 获取首页菜单列表
   * @return
   */
  @GetMapping("/tree")
  public JsonObjectBase getIndexMenu(){
    Menu menuTree = menuService.getMenuTree(null);
    return buildSuccess(menuTree);
  }

}

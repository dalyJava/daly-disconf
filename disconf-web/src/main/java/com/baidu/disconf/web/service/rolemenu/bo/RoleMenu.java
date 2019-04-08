package com.baidu.disconf.web.service.rolemenu.bo;

import com.baidu.dsp.common.dao.Columns;
import com.baidu.dsp.common.dao.DB;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.github.knightliao.apollo.db.bo.BaseObject;
import lombok.Data;

@Data
@Table(db = DB.DB_NAME, name = "role_menu")
public class RoleMenu extends BaseObject<Long> {

  @Column(value = Columns.ROLE_ID)
  private Long roleId;

  @Column(value = Columns.MENU_ID)
  private Long menuId;
}

package com.baidu.disconf.web.service.menu.bo;

import com.baidu.dsp.common.dao.Columns;
import com.baidu.dsp.common.dao.DB;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.github.knightliao.apollo.db.bo.BaseObject;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Table(db = DB.DB_NAME, keyColumn = Columns.MENU_ID, name = "menu")
@Data
public class Menu extends BaseObject<Long> implements Serializable {

  @Column(value = Columns.NAME)
  private String name;
  @Column(value = Columns.PARENT_ID)
  private int parentId;
  @Column(value = Columns.URL)
  private String url;
  @Column(value = Columns.STATUS)
  private int status;
  @Column(value = Columns.ICON)
  private String icon;
  @Column(value = Columns.CREATE_TIME)
  private String createTime;
  @Column(value = Columns.CREATE_BY)
  private String createBy;
  @Column(value = Columns.UPDATE_TIME)
  private String updateTime;
  @Column(value = Columns.UPDATE_BY)
  private String updateBy;

  private List<Menu> child;



}

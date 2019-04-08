package com.baidu.disconf.web.service.user.bo;

import com.baidu.disconf.web.common.Constants;
import com.baidu.disconf.web.service.app.bo.App;
import com.baidu.disconf.web.service.app.vo.AppListVo;
import com.baidu.disconf.web.service.role.bo.Role;
import com.baidu.dsp.common.dao.Columns;
import com.baidu.dsp.common.dao.DB;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.github.knightliao.apollo.db.bo.BaseObject;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * 用户表
 *
 * @author liaoqiqi
 * @version 2013-11-28
 */
@Data
@Table(db = DB.DB_NAME, name = "user", keyColumn = Columns.USER_ID)
public class User extends BaseObject<Long> {

  // 唯一
  @Column(value = Columns.NAME)
  private String name;

  // token
  @Column(value = Columns.TOKEN)
  private String token;

  // 密码
  @Column(value = Columns.PASSWORD)
  private String password;

  @Column(value = Columns.STATUS)
  private int status = Constants.STATUS_NORMAL;

  @Column(value = Columns.EMAILS)
  private String emails;

  @Column(value = Columns.MOBILE_PHONE)
  private String mobilePhone;

  private Set<AppListVo> appSet;

  private List<Role> roleList;

}

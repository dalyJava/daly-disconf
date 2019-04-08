package com.baidu.disconf.web.service.userappenv.bo;

import com.baidu.dsp.common.dao.Columns;
import com.baidu.dsp.common.dao.DB;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.github.knightliao.apollo.db.bo.BaseObject;
import lombok.Data;

@Data
@Table(db = DB.DB_NAME , name = "user_app_env" )
public class UserAppEnv extends BaseObject<Long> {

  @Column(value = Columns.USER_ID)
  private Long userId;

  @Column(value = Columns.APP_ID)
  private Long appId;

  @Column(value = Columns.ENV_ID)
  private Long envId;


}

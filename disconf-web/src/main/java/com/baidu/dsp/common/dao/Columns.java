package com.baidu.dsp.common.dao;

/**
 * @author liaoqiqi
 * @version 2014-1-14
 */
public interface Columns {

  String CREATE_TIME = "create_time";
  String UPDATE_TIME = "update_time";
  String CREATE_BY = "create_by";
  String UPDATE_BY = "update_by";

  String NAME = "name";

  String VERSION = "version";

  String DESC = "description";

  String VALUE = "value";

  String APP_ID = "app_id";
  String ENV_ID = "env_id";
  String CONFIG_ID = "config_id";
  String USER_ID = "user_id";
  String MENU_ID = "menu_id";

  String TYPE = "type";

  String STATUS = "status";

  String TOKEN = "token";
  String PASSWORD = "password";

  String EMAILS = "emails";

  String ROLE_ID = "role_id";

  String MOBILE_PHONE = "mobilePhone";

  String PARENT_ID = "parent_id";

  String URL = "url";

  String ICON = "icon";

  interface RoleColumns {

    String ROLE_NAME = "role_name";
  }
}

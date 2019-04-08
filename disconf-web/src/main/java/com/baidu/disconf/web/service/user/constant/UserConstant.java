package com.baidu.disconf.web.service.user.constant;

/**
 * @author liaoqiqi
 * @version 2014-1-13
 */
public interface UserConstant {

    /**
     * 是白名单用户
     */
    int IS_WHITE_USER = 1;

    /**
     * 不是白名单用户
     */
    int IS_NOT_WHITE_USER = 0;

    /**
     * 在session中缓存visitor的key
     */
    String USER_KEY = "user_key";

    /**
     * 系统更新的数据统一用这个
     */
    String USER_SYSTEM = "SYSTEM";

    //
    //
    //
    String USER_APP_SEP = ",";

}

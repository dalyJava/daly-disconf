package com.baidu.dsp.common.constant;

/**
 * @author liaoqiqi
 * @version 2013-11-26
 */
public interface WebConstants {
    /**
     * 与FE的通信时间格式统一使用yyyyMMddHHmmss
     */
    String TIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * 与FE通信的表单的Field List名称
     */
    String FIELD_LIST_NAME = "fieldList";

    /**
     * 服务端Controller前缀
     */
    String API_PREFIX = "/api";

    String UN_AUTH = "unAuth";

    String SESSION_VERIFY_CODE = "verifyCode";

    String ERROR_MESSAGE = "error";
}

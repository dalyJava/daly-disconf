package com.baidu.dsp.common.controller;

import lombok.Data;

@Data
public class CodeMsg {

  private int code;
  private String msg;

  private CodeMsg(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }


  public static CodeMsg SUCCESS = instance(200, "成功");
  public static CodeMsg FAIL = instance(400, "失败");
  public static CodeMsg UN_AUTH = instance(401, "用户[%s],没有登录或登录已过期");
  public static CodeMsg NO_AUTH = instance(403, "禁止访问");



  private static CodeMsg instance(int code, String msg) {
    return new CodeMsg(code, msg);
  }

  public CodeMsg fillArgs(Object... args) {
    String msg = String.format(this.getMsg(), args);
    return instance(this.getCode(), msg);
  }

}

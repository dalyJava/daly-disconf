package com.baidu.dsp.common.controller;

import java.io.Serializable;
import lombok.Data;

@Data
public class Result<T> implements Serializable {
  private int code;
  private String msg;
  private T data;


  public Result(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Result(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public Result(CodeMsg codeMsg, T data) {
    if (codeMsg != null) {
      this.code = codeMsg.getCode();
      this.msg = codeMsg.getMsg();
    }
    this.data = data;
  }

  private Result(CodeMsg codeMsg) {
    if (codeMsg != null) {
      this.code = codeMsg.getCode();
      this.msg = codeMsg.getMsg();
    }
  }

  public Result() {}


  public static Result success() {
    return new Result(CodeMsg.SUCCESS);
  }

  public static <T> Result<T> success(T data) {
    return new Result(CodeMsg.SUCCESS, data);
  }

  public static Result unAuth() {
    return new Result(CodeMsg.UN_AUTH);
  }

  public static Result fail() {
    return new Result(CodeMsg.FAIL);
  }

  public static <T> Result<T> fail(T data) {
    return new Result(CodeMsg.FAIL, data);
  }

}

package com.baidu.disconf.web.service.sign.type;


public enum SigninTypeEnum {
  ACCOUNT(1,"账户登录"),
  MOBILE_PHONE(2,"手机号登录"),
  EMAIL(3,"邮箱登录");

  private int type;
  private String typeName;

  SigninTypeEnum(int type, String typeName) {
    this.type = type;
    this.typeName = typeName;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
}

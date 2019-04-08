package com.baidu.disconf.web.web.auth;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Console;
import com.baidu.disconf.web.utils.ShiroUtils;
import com.baidu.dsp.common.controller.CodeMsg;
import com.baidu.dsp.common.controller.Result;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import javax.validation.constraints.NotBlank;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.form.PasswordModifyForm;
import com.baidu.disconf.web.service.user.service.UserMgr;
import com.baidu.disconf.web.web.auth.validator.AuthValidator;
import com.baidu.dsp.common.constant.ErrorCode;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaoqiqi
 * @version 2014-1-20
 */
@RestController
@RequestMapping(WebConstants.API_PREFIX + "/account")
public class UserController extends BaseController {

  protected static final Logger LOG = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserMgr userMgr;

  @Autowired
  private AuthValidator authValidator;

  /**
   * GET 获取
   */
  @RequestMapping(value = "/session", method = RequestMethod.GET)
  public JsonObjectBase get() {
    User currentUser = (User) SecurityUtils.getSubject();
    if (currentUser != null) {
      return buildSuccess("currentUser", currentUser);
    } else {
      // 没有登录啊
      return buildGlobalError("syserror.inner", ErrorCode.GLOBAL_ERROR);
    }
  }

  /**
   * 登录
   */
  @RequestMapping(value = "/signin", method = RequestMethod.POST)
  public JsonObjectBase signin(@NotBlank @RequestParam("username") String username,
      @NotBlank @RequestParam("password") String password,
      @NotBlank @RequestParam("verifyCode") String verifyCode) {
    LOG.info("username == {}, password == {}, verifyCode == {} ", username, password, verifyCode);
    String sessionVerifyCode = String.valueOf(SecurityUtils.getSubject().getSession()
        .getAttribute(WebConstants.SESSION_VERIFY_CODE));
    if (StringUtils.isBlank(sessionVerifyCode)) {
      return buildFail(WebConstants.ERROR_MESSAGE,"验证码获取失败！");
    }
    String[] varifyCodeInfo = sessionVerifyCode.split("::");
    long currentTimeMillis = System.currentTimeMillis();
    if (Long.valueOf(varifyCodeInfo[1]) < currentTimeMillis) {
      return buildFail(WebConstants.ERROR_MESSAGE,"验证码已过期！");
    }
    if (!StringUtils.equals(verifyCode, varifyCodeInfo[0])) {
      //return buildFail(WebConstants.ERROR_MESSAGE,"验证码错误！");
    }
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    try {
      SecurityUtils.getSubject().login(token);
    } catch (AuthenticationException e) {
      throw new AuthenticationException();
    }
    Object principal = SecurityUtils.getSubject().getPrincipal();

    return buildSuccess("currentUser", principal);
  }

  /**
   * 登出
   */
  @RequestMapping(value = "/signout", method = RequestMethod.GET)
  public JsonObjectBase signout() {
    SecurityUtils.getSubject().logout();
    return buildSuccess("ok", "ok");
  }

  /**
   * 修改密码
   */
  @RequestMapping(value = "/password", method = RequestMethod.PUT)
  public JsonObjectBase password(@Valid PasswordModifyForm passwordModifyForm,
      HttpServletRequest request) {

    // 校验
    authValidator.validatePasswordModify(passwordModifyForm);

    // 修改
    User currentUser = ShiroUtils.getCurrentUser();
    userMgr.modifyPassword(currentUser.getId(), passwordModifyForm.getNew_password());
    ShiroUtils.loginOut();
    return buildSuccess("修改成功，请重新登录");
  }

  @RequestMapping(WebConstants.UN_AUTH)
  public JsonObjectBase unAuth() {
    return buildSuccess(Result.unAuth());
  }

  @RequestMapping("/hasAuth/{url}")
  public JsonObjectBase hasAuth(@PathVariable("url") String url) {
    boolean hasPerm = ShiroUtils.checkPerissionAny(url);
    return hasPerm ? buildSuccess(Result.success()) : buildSuccess(Result.fail(CodeMsg.NO_AUTH));
  }

  @GetMapping("/verifyCode")
  public String getVerifyCode(){
    LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 35,4,20);
    String code = lineCaptcha.getCode() + "::" + (System.currentTimeMillis() + 5 * 60 * 1000);
    SecurityUtils.getSubject().getSession().setAttribute(WebConstants.SESSION_VERIFY_CODE, code);
    String imageBase64 = lineCaptcha.getImageBase64();
    return imageBase64;
  }


}

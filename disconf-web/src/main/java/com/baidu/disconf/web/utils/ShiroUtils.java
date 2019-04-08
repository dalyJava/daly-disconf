package com.baidu.disconf.web.utils;

import com.baidu.disconf.web.service.role.bo.Role;
import com.baidu.disconf.web.service.user.bo.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.naming.NoPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

@Slf4j
public class ShiroUtils {

  public static List<Long> getCurrentUserRoleIds(){
    List<Role> roleList = getCurrentUser().getRoleList();
    List<Long> roleIdsList = new ArrayList<>();
    roleList.stream().forEach(role -> roleIdsList.add(role.getId()));
    return roleIdsList;
  }


  public static User getCurrentUser() {
    Subject subject = SecurityUtils.getSubject();
    Object principal = subject.getPrincipal();
    return (User) principal;
  }

  public static Session getSession() throws Exception {
    return Optional.ofNullable(SecurityUtils.getSubject())
        .map(subject -> subject.getSession())
        .orElseThrow(() -> new Exception("会话已过期"));
  }

  public static void loginOut() {
    SecurityUtils.getSubject().logout();
  }

  /**
   * 检查当前用户是否有权限(任意一项)
   *
   * @param permissionCodes 任意权限
   */
  public static boolean checkPerissionAny(String... permissionCodes){
    if (permissionCodes == null || permissionCodes.length == 0) {
      return false;
    }
    // 获取用户信息
    Subject currentUser = SecurityUtils.getSubject();
    for (String permission : permissionCodes) {
      boolean permitted = currentUser.isPermitted(permission);// 判断是否有权限
      if (permitted) {
        return true;
      }
    }
    return false;
  }


}

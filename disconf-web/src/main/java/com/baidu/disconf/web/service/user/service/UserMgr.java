package com.baidu.disconf.web.service.user.service;

import java.util.List;

import com.baidu.disconf.web.service.user.bo.User;
import java.util.Set;

/**
 * @author liaoqiqi
 * @version 2013-11-28
 */
public interface UserMgr {

    User getCurrentUser();

    User getUser(Long userId);

    /**
     * @return
     */
    Long create(User user);

    /**
     * @param user
     */
    void create(List<User> user);

    /**
     * @return
     */
    List<User> getAll();

    /**
     * 为某个user添加一个app
     *
     * @param userId
     */
    String addOneAppForUser(Long userId, long appId);

    /**
     * 修改密码
     *
     * @param newPassword
     */
    void modifyPassword(Long userId, String newPassword);

    /**
     * 获取用户的APPID
     * @return
     */
    Set<Long> getUserAppIds();
}

package com.baidu.disconf.web.service.user.service.impl;

import com.baidu.disconf.web.service.app.bo.App;
import com.baidu.disconf.web.service.app.dao.AppDao;
import com.baidu.disconf.web.service.app.vo.AppListVo;
import com.baidu.disconf.web.utils.ShiroUtils;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.disconf.web.service.sign.utils.SignUtils;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.dao.UserDao;
import com.baidu.disconf.web.service.user.service.UserMgr;

/**
 * @author liaoqiqi
 * @version 2013-12-5
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserMgrImpl implements UserMgr {

    protected static final Logger LOG = LoggerFactory.getLogger(UserMgrImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private AppDao appDao;

    @Override
    public User getCurrentUser() {
        return ShiroUtils.getCurrentUser();
    }

    /**
     * 创建
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Long create(User user) {

        user = userDao.create(user);
        return user.getId();
    }

    /**
     *
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void create(List<User> users) {

        userDao.create(users);
    }

    @Override
    public List<User> getAll() {

        return userDao.findAll();
    }

    /**
     * @param userId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String addOneAppForUser(Long userId, long appId) {

        User user = getUser(userId);

        //String ownAppIds = user.getOwnApps();
        Set<AppListVo> appSet = user.getAppSet();
        App app = appDao.get(appId);
        appSet.add(app.getAppListVo());
        //待补充代码
        userDao.update(user);
        return getAppIdsStr(user);
    }

    /**
     * @param newPassword
     */
    @Override
    public void modifyPassword(Long userId, String newPassword) {

        String passwordWithSalt = SignUtils.createPassword(newPassword);

        User user = userDao.get(userId);
        user.setPassword(passwordWithSalt);

        userDao.update(user);
    }

    @Override
    public Set<Long> getUserAppIds() {
        User currentUser = ShiroUtils.getCurrentUser();
        return getAppIdsSet(currentUser);
    }

    /**
     * @param userId
     *
     * @return
     */
    @Override
    public User getUser(Long userId) {

        return userDao.get(userId);
    }

    /**
     * 获取appIds
     * @param user
     * @return Set<Long>
     */
    private Set<Long> getAppIdsSet(User user){
        Set<AppListVo> appSet = user.getAppSet();

        Set<Long> appIds = new HashSet<>();
        appSet.forEach(oneApp-> appIds.add(oneApp.getId()));
        return appIds;
    }

    /**
     * 获取appIds
     * @param user
     * @return String
     */
    private String getAppIdsStr(User user){
        StringBuffer strbuf = new StringBuffer();
        getAppIdsSet(user).forEach(appId-> strbuf.append(appId).append(","));
        if(strbuf.length()>0){
            strbuf.substring(0,strbuf.length()-1);
        }
        return String.valueOf(strbuf);
    }

}

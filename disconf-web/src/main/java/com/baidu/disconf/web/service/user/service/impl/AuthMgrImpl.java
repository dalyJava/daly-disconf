package com.baidu.disconf.web.service.user.service.impl;

import com.baidu.disconf.web.service.user.service.UserMgr;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.disconf.web.service.user.service.AuthMgr;

/**
 * @author knightliao
 */
@Service
public class AuthMgrImpl implements AuthMgr {

    @Autowired
    private UserMgr userMgr;

    @Override
    public boolean verifyApp4CurrentUser(Long appId) {

        Set<Long> idsLongs = userMgr.getUserAppIds();

        if (CollectionUtils.isEmpty(idsLongs)) {
            return true;
        }

        if (idsLongs.contains(appId)) {
            return true;
        } else {
            return false;
        }

    }

}

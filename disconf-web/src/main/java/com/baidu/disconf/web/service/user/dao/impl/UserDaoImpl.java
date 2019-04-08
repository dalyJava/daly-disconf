package com.baidu.disconf.web.service.user.dao.impl;

import com.baidu.disconf.web.service.role.bo.Role;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.dao.UserDao;
import com.baidu.dsp.common.dao.AbstractDao;
import com.baidu.dsp.common.dao.Columns;

/**
 * @author liaoqiqi
 * @version 2013-11-28
 */
@Repository
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    /**
     * 执行SQL
     */
    public void executeSql(String sql) {
        executeSQL(sql, null);
    }

    @Override
    public User getUserByUserName(String username) {
        return findOne(match(Columns.NAME, username));
    }



    @Override
    public User getUserByEmail(String email) {
        return findOne(match(Columns.EMAILS, email));
    }

    @Override
    public User getUserByMobilePhone(String mobilePhone) {
        return findOne(match(Columns.MOBILE_PHONE, mobilePhone));
    }

    @Override
    public List<Role> getRolesByName(String name) {
        return null;
    }


}
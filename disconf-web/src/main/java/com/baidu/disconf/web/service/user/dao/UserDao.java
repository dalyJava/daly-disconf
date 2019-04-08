package com.baidu.disconf.web.service.user.dao;

import com.baidu.disconf.web.service.role.bo.Role;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.unbiz.common.genericdao.dao.BaseDao;
import java.util.List;

/**
 * @author liaoqiqi
 * @version 2013-11-28
 */
public interface UserDao extends BaseDao<Long, User> {

    void executeSql(String sql);

    User getUserByUserName(String username);

    User getUserByEmail(String email);

    User getUserByMobilePhone(String mobilePhone);

    List<Role> getRolesByName(String name);

}

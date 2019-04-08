package com.baidu.disconf.web.service.role.service.impl;

import com.baidu.disconf.web.service.roleuser.dao.RoleUserDao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.disconf.web.service.role.bo.Role;
import com.baidu.disconf.web.service.role.dao.RoleDao;
import com.baidu.disconf.web.service.role.service.RoleMgr;

/**
 *
 */
@Service
public class RoleMgrImpl implements RoleMgr {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public Role get(Long roleId) {
        return roleDao.get(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        List<Long> roleIds = roleUserDao.findRoleIds(userId);
        List<Role> roleList = new ArrayList<>();
        roleIds.stream().forEach(roleId->{
            Role role = get(roleId);
            roleList.add(role);
        });
        return roleList;
    }

}

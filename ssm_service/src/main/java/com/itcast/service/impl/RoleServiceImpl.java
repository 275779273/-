package com.itcast.service.impl;

import com.github.pagehelper.PageHelper;
import com.itcast.dao.IRoleDao;
import com.itcast.domain.Permission;
import com.itcast.domain.Role;
import com.itcast.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(int page,int size) {
        PageHelper.startPage ( page,size );
        List<Role> roleList = roleDao.findAll ();
        return roleList;
    }

    @Override
    public void saveRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findNotRole(String id) {
        List<Permission> permissionList = roleDao.findNotRole(id);
        return permissionList;
    }

    @Override
    public void addPermission(String roleId,String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            roleDao.addPermission(roleId,ids[i]);
        }
    }
}

package com.itcast.service.impl;

import com.github.pagehelper.PageHelper;
import com.itcast.dao.IPermissionDao;
import com.itcast.domain.Permission;
import com.itcast.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page,int size) {
        PageHelper.startPage ( page,size );
        List<Permission> permissionList = permissionDao.findAll ();
        return permissionList;
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}

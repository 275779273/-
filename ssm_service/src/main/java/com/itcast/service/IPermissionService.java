package com.itcast.service;

import com.itcast.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll(int page,int size);

    void save(Permission permission);
}

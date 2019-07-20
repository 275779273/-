package com.itcast.service;

import com.itcast.domain.Permission;
import com.itcast.domain.Role;

import java.util.List;


public interface IRoleService {
    List<Role> findAll(int page,int size);

    void saveRole(Role role);

    List<Permission> findNotRole(String id);

    void addPermission(String roleId,String[] ids);
}

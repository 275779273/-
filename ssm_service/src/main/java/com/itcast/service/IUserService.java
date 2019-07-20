package com.itcast.service;

import com.itcast.domain.Role;
import com.itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll(int page, int size);

    UserInfo findById(String id);

    void save(UserInfo userInfo);

    List<Role> findNotRole(String id);

    void addRole(String userId, String[] roleIds);
}

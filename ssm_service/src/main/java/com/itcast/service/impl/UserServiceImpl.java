package com.itcast.service.impl;

import com.github.pagehelper.PageHelper;
import com.itcast.dao.IUserDao;
import com.itcast.domain.Role;
import com.itcast.domain.UserInfo;
import com.itcast.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByName ( username );
        User user = null;
        if(userInfo.getPassword ().length ()==60){
            user = new User (userInfo.getUsername (),"{bcrypt}"+userInfo.getPassword (),getAuthorities(userInfo.getRoles ()));
        }else {
            user = new User (userInfo.getUsername (),"{noop}"+userInfo.getPassword (),getAuthorities(userInfo.getRoles ()));
        }
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<> (  );
        for (Role role : roles) {
            //传入角色名称存入集合
            list.add ( new SimpleGrantedAuthority ( "ROLE_"+role.getRoleName () ) );
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(int page,int size) {
        PageHelper.startPage ( page,size );
        List<UserInfo> userList = userDao.findAll();

        return userList;
    }

    @Override
    public UserInfo findById(String id) {
        UserInfo user = userDao.findById ( id );
        return user;
    }

    @Override
    public void save(UserInfo userInfo) {
        //存入加密后的密码
        userInfo.setPassword ( passwordEncoder.encode ( userInfo.getPassword () ) );
        userDao.save(userInfo);
    }

    @Override
    public List<Role> findNotRole(String id) {
        List<Role> roleList = userDao.findNotRole(id);
        return roleList;
    }

    @Override
    public void addRole(String userId, String[] roleIds) {
        //给用户添加角色
        for (int i = 0; i < roleIds.length; i++) {
            userDao.addRole ( userId,roleIds[i] );
        }
    }
}

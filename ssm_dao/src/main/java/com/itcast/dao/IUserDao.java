package com.itcast.dao;

import com.itcast.domain.Role;
import com.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {

    @Select ( "select * from users where username=#{username} and status=1" )
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                many = @Many(select = "com.itcast.dao.IRoleDao.findRolesByUserId")
            )
    })
    UserInfo findByName(String username);


    @Select ( "select * from users " )
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
            many = @Many(select = "com.itcast.dao.IRoleDao.findRolesByUserId"))
    })
    List<UserInfo> findAll();

    @Select ( "select * from users where id=#{id} " )
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.itcast.dao.IRoleDao.findRolesByUserId")
            )
    })
    UserInfo findById(String id);

    @Insert ( "insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})" )
    void save(UserInfo user);

    @Insert ( "insert into users_role values(#{userId},#{roleId}) " )
    void addRole(@Param ( "userId" ) String userId,@Param ( "roleId" ) String roleId);

    @Select ( "select * from role where id not in (select roleId from users_role where userId=#{id})" )
    List<Role> findNotRole(String id);
}

package com.itcast.dao;

import com.itcast.domain.Permission;
import com.itcast.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId=#{userId}) ")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.itcast.dao.IPermissionDao.findAllByRoleId"))
    })
    List<Role> findRolesByUserId(String userId);

    @Select("select * from role ")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc)values(#{roleName},#{roleDesc}) ")
    void save(Role role);

    @Select ( "select * from permission where id not in (select permissionId from role_permission where roleId=#{id})" )
    List<Permission> findNotRole(String id);

    @Insert ( "insert into role_permission values(#{permissionId},#{roleId})" )
    void addPermission(@Param ( "roleId" ) String roleId,@Param ( "permissionId" ) String permissionId);
}



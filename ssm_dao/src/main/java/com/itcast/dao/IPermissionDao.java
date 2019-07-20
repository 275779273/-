package com.itcast.dao;

import com.itcast.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {

    @Select ( "select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})" )
    List<Permission> findAllByRoleId(String roleId);

    @Select ( "select * from permission" )
    List<Permission> findAll();

    @Insert ( "insert into permission (permissionName,url) values (#{permissionName},#{url})" )
    void save(Permission permission);
}

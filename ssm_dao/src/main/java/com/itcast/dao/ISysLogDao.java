package com.itcast.dao;

import com.itcast.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {
    @Insert ( "insert into sysLog (visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method}) " )
    void save(SysLog sysLog);

    @Select ( "select * from sysLog order by visittime desc " )
    List<SysLog> findAll();
}

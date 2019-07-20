package com.itcast.dao;

import com.itcast.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {

    /**
     * 根据id查询会员表
     * @param id
     * @return
     */
    @Select ( "select * from member where id=#{id}" )
    Member findMemberById(String id);
}

package com.itcast.service;

import com.itcast.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll(Integer page, Integer size);
}

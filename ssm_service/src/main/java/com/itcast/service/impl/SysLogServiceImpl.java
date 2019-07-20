package com.itcast.service.impl;

import com.github.pagehelper.PageHelper;
import com.itcast.dao.ISysLogDao;
import com.itcast.domain.SysLog;
import com.itcast.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save ( sysLog );
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer size) {
        PageHelper.startPage ( page,size );
        List<SysLog> sysLogList = sysLogDao.findAll();
        return sysLogList;
    }
}

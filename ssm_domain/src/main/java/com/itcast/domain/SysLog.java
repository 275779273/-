package com.itcast.domain;

import com.itcast.utils.DateUtils;

import java.util.Date;

//日志表
public class SysLog {

    private String id;
    private Date visitTime;         //访问的时间
    private String visitTimeStr;    //
    private String username;        //访问的用户名
    private String ip;              //访问的用户的ip
    private String url;             //url
    private Long executionTime;     //访问的时长
    private String method;          //执行的方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {
        visitTimeStr = DateUtils.date2String ( visitTime, "yyyy-MM-dd HH:mm:ss" );
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}

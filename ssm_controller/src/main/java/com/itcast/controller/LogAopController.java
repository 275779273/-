package com.itcast.controller;

import com.itcast.domain.SysLog;
import com.itcast.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAopController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Method method;      //执行的方法
    private Class clazz;        //执行的类
    private Date visitTime;          //执行的开始时间


    /**
     * 前置通知主要为了获取执行的类和方法,访问时间
     * @param jp
     */
    @Before ( "execution(* com.itcast.controller.*.*(..))" )
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //获取执行的开始时间
        visitTime = new Date();

        clazz = jp.getTarget ().getClass ();      //获取执行的class类
        String methodName = jp.getSignature ().getName ();    //获取执行的方法名
        Object[] args = jp.getArgs ();                  //获取所有参数
        //获取执行的方法
        if(args==null&&args.length==0){
            //空参方法
            method = clazz.getMethod ( methodName );
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i]=args[i].getClass ();
            }
            //有参方法
            method = clazz.getMethod ( methodName, classArgs );
        }
    }

    @After ( "execution(* com.itcast.controller.*.*(..))" )
    public void doAfter(JoinPoint jp){
        //获取访问的时间
        long time = new Date ().getTime () - visitTime.getTime ();

        //获取uri
        String url = "";
        if(clazz!=null&&method!=null&&clazz!=LogAopController.class&&clazz!=SysLogController.class){
            //获取RequestMapping注解中的路径
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation ( RequestMapping.class );
            if(clazzAnnotation!=null){
                String clazzPath = clazzAnnotation.value ()[0];
                RequestMapping methodAnnotation = (RequestMapping) clazz.getAnnotation ( RequestMapping.class );
                if(methodAnnotation!=null){
                    String methodPath = methodAnnotation.value ()[0];
                    url = clazzPath+methodPath;

                    //获取访问的用户名
                    String username = SecurityContextHolder.getContext ().getAuthentication ().getName ();
                    //获取用户ip
                    String ip = request.getRemoteAddr ();
                    SysLog sysLog = new SysLog ();

                    sysLog.setUrl ( url );              //url
                    sysLog.setExecutionTime ( time );   //访问时长
                    sysLog.setVisitTime ( visitTime );  //访问的时间
                    sysLog.setMethod ( "[类名] " + clazz.getName() + "[方法名] " + method.getName() );   //执行的方法
                    sysLog.setUsername ( username );    //访问的用户的名字
                    sysLog.setIp ( ip );                //访问的用户的ip地址
                    //执行添加日志
                    sysLogService.save ( sysLog );
                }
            }
        }
    }
}

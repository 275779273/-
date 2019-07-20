package com.itcast.controller;

import com.github.pagehelper.PageInfo;
import com.itcast.domain.Role;
import com.itcast.domain.UserInfo;
import com.itcast.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
@Transactional  //添加事务管理
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView ();

        List<UserInfo> userList = userService.findAll ( page, size );
        PageInfo pageInfo = new PageInfo ( userList );
        mv.addObject ( "pageInfo", pageInfo );
        mv.setViewName ( "user-list" );
        return mv;
    }

    /**
     * 查询单个用户的详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) {
        ModelAndView mv = new ModelAndView ();
        UserInfo user = userService.findById ( id );
        mv.addObject ( "user", user );
        mv.setViewName ( "user-show" );
        return mv;
    }


    /**
     * 添加用户 , 添加完再重新调用查询所有用户的方法
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("/save.do")
    public ModelAndView saveUser(HttpServletRequest request, UserInfo userInfo) {
        userService.save ( userInfo );
        PageInfo pageInfo = (PageInfo) request.getAttribute ( "pageInfo" );
        int pageSize = pageInfo.getPageSize ();
        System.out.println ( "pageSize = " + pageSize );
        ModelAndView mv = findAll ( 1, 4 );
        return mv;
    }

    /**
     * 查询用户没有的角色
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findNotRole(@RequestParam(name = "id",required = true)String id){
        ModelAndView mv = new ModelAndView ();
        //查询用户没有的角色
        List<Role> roleList = userService.findNotRole(id);
        //根据userId查询用户信息
        UserInfo user = userService.findById ( id );
        mv.addObject ( "roleList",roleList );
        mv.addObject ( "user",user );
        mv.setViewName ( "user-role-add" );
        return mv;
    }

    /**
     * 给用户添加角色
     * @return
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRole(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] roleIds){
        ModelAndView mv = new ModelAndView ( );
        userService.addRole(userId,roleIds);
        return "redirect:findAll.do";
    }
}

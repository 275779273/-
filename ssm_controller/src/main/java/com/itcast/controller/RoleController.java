package com.itcast.controller;

import com.github.pagehelper.PageInfo;
import com.itcast.domain.Permission;
import com.itcast.domain.Role;
import com.itcast.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 查询所有角色
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView ();
        List<Role> roleList = roleService.findAll ( page, size );
        PageInfo<Role> rolePageInfo = new PageInfo<> ( roleList );
        mv.addObject ( "rolePageInfo", rolePageInfo );
        mv.setViewName ( "role-list" );
        return mv;
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public ModelAndView saveRole(Role role) {
        roleService.saveRole ( role );
        ModelAndView mv = findAll ( 1, 4 );
        return mv;
    }

    @RequestMapping("/findNotRole")
    public ModelAndView findNotRole(@RequestParam(name = "roleId",required = true)String roleId){
        ModelAndView mv = new ModelAndView ();
        //查询没有的权限
        List<Permission> permissionList = roleService.findNotRole(roleId);

        mv.addObject ( "permissionList",permissionList );
        mv.addObject ( "roleId",roleId );
        mv.setViewName ( "role-permission-add" );
        return mv;
    }

    /**
     * 给指定id的角色添加权限
     * @return
     */
    @RequestMapping("/addPermission")
    public String addPermission(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] ids){

        roleService.addPermission(roleId,ids);
        return "redirect:findAll.do";
    }
}

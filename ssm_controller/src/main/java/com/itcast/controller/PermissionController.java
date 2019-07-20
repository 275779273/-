package com.itcast.controller;

import com.github.pagehelper.PageInfo;
import com.itcast.domain.Permission;
import com.itcast.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView ();
        List<Permission> permissionList = permissionService.findAll ( page, size );
        PageInfo<Permission> permissionPageInfo = new PageInfo<> ( permissionList );
        mv.addObject ( "permissionPageInfo", permissionPageInfo );
        mv.setViewName ( "permission-list" );
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission){

        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}

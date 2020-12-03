package com.wofang.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wofang.mybatisplus.model.UserInfo;
import com.wofang.mybatisplus.service.UserInfoService;
import com.wofang.mybatisplus.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Api("indexController")
@RestController
public class IndexController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("")
    public ModelAndView none(){
        return index();
    }
    @ApiOperation(value = "desc of method", notes = "")
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    //@RequestMapping("/queryUserInfo")
    public Object queryUserInfo(){
        List<UserInfo> list = userInfoService.queryUserInfo();
        return ResponseUtil.success(list);
    }

    //@RequestMapping("queryUserInfoByName")
    public Object queryUserInfoByName(String userName){
        QueryWrapper<UserInfo> query = new QueryWrapper<>();
        query.lambda().eq(UserInfo::getUserName,"admin").orderByAsc(UserInfo::getUserId);


        List<UserInfo> list = userInfoService.list(query);
        IPage<UserInfo> page = new Page<>();

        userInfoService.page(page,query);

        return ResponseUtil.success(list);
    }
}

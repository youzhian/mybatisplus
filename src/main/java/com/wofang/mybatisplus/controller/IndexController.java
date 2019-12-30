package com.wofang.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wofang.mybatisplus.model.UserInfo;
import com.wofang.mybatisplus.service.UserInfoService;
import com.wofang.mybatisplus.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/")
    public Object index(){
        return ResponseUtil.success();
    }

    @RequestMapping("/queryUserInfo")
    public Object queryUserInfo(){
        List<UserInfo> list = userInfoService.queryUserInfo();
        return ResponseUtil.success(list);
    }

    @RequestMapping("queryUserInfoByName")
    public Object queryUserInfoByName(String userName){
        QueryWrapper<UserInfo> query = new QueryWrapper<>();
        query.lambda().eq(UserInfo::getUserName,"admin").orderByAsc(UserInfo::getUserId);


        List<UserInfo> list = userInfoService.list(query);
        IPage<UserInfo> page = new Page<>();

        userInfoService.page(page,query);

        return ResponseUtil.success(list);
    }
}

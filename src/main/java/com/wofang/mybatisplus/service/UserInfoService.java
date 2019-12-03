package com.wofang.mybatisplus.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wofang.mybatisplus.model.UserInfo;

import java.util.List;

public interface UserInfoService extends IService<UserInfo> {

    public List<UserInfo> queryUserInfo();
}

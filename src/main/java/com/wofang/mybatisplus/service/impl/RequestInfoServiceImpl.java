package com.wofang.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wofang.mybatisplus.mapper.RequestInfoMapper;
import com.wofang.mybatisplus.mapper.UserInfoMapper;
import com.wofang.mybatisplus.model.RequestInfo;
import com.wofang.mybatisplus.service.RequestInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestInfoServiceImpl extends ServiceImpl<RequestInfoMapper,RequestInfo> implements RequestInfoService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(RequestInfoServiceImpl.class);

    @Autowired
    private RequestInfoMapper requestInfoMapper;
}

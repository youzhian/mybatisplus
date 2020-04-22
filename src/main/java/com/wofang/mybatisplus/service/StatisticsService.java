package com.wofang.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wofang.mybatisplus.model.RequestStatistics;

public interface StatisticsService extends IService<RequestStatistics> {

    public RequestStatistics add(RequestStatistics rs);


}

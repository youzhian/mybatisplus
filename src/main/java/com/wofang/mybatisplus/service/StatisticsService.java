package com.wofang.mybatisplus.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wofang.mybatisplus.model.RequestStatistics;

import java.util.List;

public interface StatisticsService extends IService<RequestStatistics> {

    public RequestStatistics add(RequestStatistics rs);

    /**
     * 根据关键字分组，统计总量
     * @param name 名称
     * @param currDate 当前时间（天）
     * @param currHour 当前时间（精确到小时）
     * @return
     */
    public List<JSONObject> getCountGroupByKeyword(String name, String currDate, String currHour);
}

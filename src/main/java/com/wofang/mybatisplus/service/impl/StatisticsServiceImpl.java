package com.wofang.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wofang.mybatisplus.mapper.RequestStatisticsMapper;
import com.wofang.mybatisplus.model.RequestStatistics;
import com.wofang.mybatisplus.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("statistics")
public class StatisticsServiceImpl extends ServiceImpl<RequestStatisticsMapper, RequestStatistics> implements StatisticsService {

    @Autowired
    private RequestStatisticsMapper statisticsMapper;

    @Override
    public RequestStatistics add(RequestStatistics rs) {
        if(rs != null){
            statisticsMapper.addRequest(rs);
        }
        return rs;
    }
}

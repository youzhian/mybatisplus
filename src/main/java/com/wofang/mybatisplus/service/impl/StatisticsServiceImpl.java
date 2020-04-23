package com.wofang.mybatisplus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wofang.mybatisplus.mapper.RequestStatisticsMapper;
import com.wofang.mybatisplus.model.RequestStatistics;
import com.wofang.mybatisplus.service.StatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<JSONObject> getCountGroupByKeyword(String name,String currDate,String currHour){
        /**
         * 总数
         */
        List<RequestStatistics> totalList = statisticsMapper.queryCountGroupByKeyword(name,null,null);
        /**
         * 今日总数
         */
        List<RequestStatistics> daytotalList = statisticsMapper.queryCountGroupByKeyword(name,currDate,null);
        /**
         * 当前时段总数
         */
        List<RequestStatistics> hourtotalList = statisticsMapper.queryCountGroupByKeyword(name,currDate,currHour);

        Map<String,Integer> totalMap =  totalList.stream().collect(Collectors.toMap(RequestStatistics::getKeyword,RequestStatistics::getCount));
        Map<String,Integer> daytotalMap =  daytotalList.stream().collect(Collectors.toMap(RequestStatistics::getKeyword,RequestStatistics::getCount));
        Map<String,Integer> hourtotalMap =  hourtotalList.stream().collect(Collectors.toMap(RequestStatistics::getKeyword,RequestStatistics::getCount));

        List<JSONObject> result = new ArrayList<>();

        for(Map.Entry<String,Integer> entry : totalMap.entrySet()){
            String keyword = entry.getKey();
            Integer count = entry.getValue();
            /**
             * 当日总数
             */
            Integer dayCount = daytotalMap.get(keyword);
            if(dayCount == null){
                dayCount = 0;
            }
            //当前时段总数
            Integer hourCount = hourtotalMap.get(keyword);
            if(hourCount == null){
                hourCount = 0;
            }
            JSONObject data = new JSONObject();
            if(StringUtils.isBlank(keyword)){
                keyword = "未分组";
            }
            data.put("keyword",keyword);
            data.put("totalCount",count.intValue());
            data.put("dayCount",dayCount.intValue());
            data.put("hourCount",hourCount.intValue());

            result.add(data);
        }

        return result;

    }
}

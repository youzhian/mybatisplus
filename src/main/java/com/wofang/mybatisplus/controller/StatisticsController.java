package com.wofang.mybatisplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wofang.mybatisplus.model.RequestStatistics;
import com.wofang.mybatisplus.service.StatisticsService;
import com.wofang.mybatisplus.util.DateUtil;
import com.wofang.mybatisplus.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 数据统计controller
 */
@RestController
@RequestMapping("statistics")
@CrossOrigin
public class StatisticsController extends BaseController {

    @Autowired
    private StatisticsService statisticsService;
    /**
     * 新增统计信息
     * @param statistics
     * @return
     */
    @RequestMapping("addRequest")
    public JSONObject addRequest(RequestStatistics statistics, HttpServletRequest request){
        if(statistics != null && StringUtils.isNotBlank(statistics.getName())){
            String ip = request.getRemoteHost();
            //当前时间
            Date date = new Date();
            statistics.setRequestIp(ip);
            statistics.setCreateDate(date);
            statistics.setCreateTime(date);
            //新增
            statisticsService.save(statistics);
        }
        return ResponseUtil.success("增加成功");
    }

    /**
     * 查询jfcc的统计数据
     * @return
     */
    @RequestMapping("statisticsJFCC")
    public ModelAndView statisticsJFCC() throws Exception {
        ModelAndView mv = new ModelAndView("/statistics/jfcc");
        //当前时间
        Date date = new Date();
        String name = "jfcc1.jk825.top";
        QueryWrapper<RequestStatistics> query = new QueryWrapper<>();
        query.eq("name",name);
        //总数
        long total = statisticsService.count(query);
        query.eq("create_date", DateUtil.parseDate(date));
        //查询当日数据量
        long daytotal = statisticsService.count(query);



        //总数
        mv.addObject("total",total);
        //日总数
        mv.addObject("daytotal",daytotal);

        return mv;
    }
}

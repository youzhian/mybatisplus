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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
            //当前请求地址
            statistics.setRequestIp(ip);
            //新增
            statisticsService.add(statistics);
        }
        return ResponseUtil.success("增加成功");
    }

    /**
     * 查询jfcc的统计数据
     * @return
     */
    @RequestMapping("statisticsJFCC")
    public ModelAndView statisticsJFCC() throws Exception {
        ModelAndView mv = new ModelAndView("statistics/jfcc");
        //当前时间
        Date date = new Date();
        String name = "jfcc1.jk825.top";
        QueryWrapper<RequestStatistics> query = new QueryWrapper<>();
        query.eq("name",name);
        //总数
        long total = statisticsService.count(query);

        String currDate = DateUtil.parseDate(date);

        query.eq("create_date", currDate);
        //查询当日数据量
        long daytotal = statisticsService.count(query);
        //精确到时
        String milin = DateUtil.getStringDate(date,"yyyy-MM-dd HH");

        query.lambda().ge(RequestStatistics::getCreateTime,milin);
        //当前时间段数量
        long hourtotal = statisticsService.count(query);
        //总数
        mv.addObject("total",total);
        //日总数
        mv.addObject("daytotal",daytotal);
        //时间总数
        mv.addObject("hourtotal",hourtotal);
        mv.addObject("time",milin+":00:00~"+milin+":59:59");
        //查询每个微信号的统计个数
        List<JSONObject> list = statisticsService.getCountGroupByKeyword(name,currDate,milin);
        mv.addObject("list",list);
        return mv;
    }
}

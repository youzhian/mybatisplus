package com.wofang.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wofang.mybatisplus.model.RequestStatistics;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface RequestStatisticsMapper extends BaseMapper<RequestStatistics> {

    @Insert(value="insert into request_statistics (name,rs_desc,request_ip,create_date,create_time)values(#{name},#{rsDesc},#{requestIp},curdate(),now())")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public int addRequest(RequestStatistics rs);
}

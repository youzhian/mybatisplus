package com.wofang.mybatisplus.mapper;

import com.wofang.mybatisplus.model.Area;
import com.wofang.mybatisplus.model.City;
import com.wofang.mybatisplus.model.Province;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceCityCountyMapper {

    @Insert("insert into province_city_county (code,name,level_num) values(#{code},#{name},1)")
    public int addProvince(Province province);
    @Insert("insert into province_city_county (code,name,parent_code,level_num) values(#{code},#{name},#{parentCode},#{levelNum})")
    public int addCity(City city);
    @Insert("insert into province_city_county (code,name,parent_code,level_num) values(#{code},#{name},#{parentCode},#{levelNum})")
    public int addCounty(Area county);
}

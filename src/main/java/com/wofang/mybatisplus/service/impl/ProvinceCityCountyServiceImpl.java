package com.wofang.mybatisplus.service.impl;

import com.wofang.mybatisplus.mapper.ProvinceCityCountyMapper;
import com.wofang.mybatisplus.model.Area;
import com.wofang.mybatisplus.model.City;
import com.wofang.mybatisplus.model.Province;
import com.wofang.mybatisplus.service.ProvinceCityCountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("provinceCityCountyService")
public class ProvinceCityCountyServiceImpl implements ProvinceCityCountyService {
    /**
     * mapper
     */
    @Autowired
    private ProvinceCityCountyMapper provinceCityCountyMapper;
    /**
     * 新增省份
     *
     * @param province
     * @return
     */
    @Override
    public boolean addProvince(Province province) {
        int result = provinceCityCountyMapper.addProvince(province);
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 新增城市
     *
     * @param city
     * @return
     */
    @Override
    public boolean addCity(City city) {
        int result = provinceCityCountyMapper.addCity(city);
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 新增区县
     *
     * @param area
     * @return
     */
    @Override
    public boolean addCounty(Area area) {
        int result = provinceCityCountyMapper.addCounty(area);
        if(result > 0){
            return true;
        }
        return false;
    }
}

package com.wofang.mybatisplus.service;

import com.wofang.mybatisplus.model.Area;
import com.wofang.mybatisplus.model.City;
import com.wofang.mybatisplus.model.Province;

public interface ProvinceCityCountyService {
    /**
     * 新增省份
     * @param province
     * @return
     */
    public boolean addProvince(Province province);

    /**
     * 新增城市
     * @param city
     * @return
     */
    public boolean addCity(City city);

    /**
     * 新增区县
     * @param area
     * @return
     */
    public boolean addCounty(Area area);
}

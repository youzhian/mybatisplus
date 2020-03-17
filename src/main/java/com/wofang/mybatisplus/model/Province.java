package com.wofang.mybatisplus.model;

import java.util.List;

/**
 * 省份
 * @author jx on 2018/4/12.
 */

public class Province {

    private String code;
    private String name;
    /**
     * 等级
     */
    private Integer leveNum;
    private List<City> cityList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLeveNum() {
        if(leveNum == null){
            return 1;
        }
        return leveNum;
    }

    public void setLeveNum(Integer leveNum) {
        this.leveNum = leveNum;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}

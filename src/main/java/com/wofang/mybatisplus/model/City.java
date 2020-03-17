package com.wofang.mybatisplus.model;

import java.util.List;

/**
 * 地级市
 * @author jx on 2018/4/12.
 */

public class City {
    private String code;
    private String name;
    /**
     * 父级code,这里为省份code
     */
    private String parentCode;
    /**
     * 等级
     */
    private Integer levelNum;
    /**
     * 区域list
     */
    private List<Area> areaList;

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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getLevelNum() {
        if(levelNum == null){
            return 2;
        }
        return levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }
}

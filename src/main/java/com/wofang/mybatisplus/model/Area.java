package com.wofang.mybatisplus.model;

/**
 * 区，县
 * @author jx on 2018/4/12.
 */

public class Area {
    private String code;
    private String name;
    /**
     * 父级code,这里为市code
     */
    private String parentCode;
    /**
     * 区县等级，默认为3
     */
    private Integer levelNum;

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
            return 3;
        }
        return levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }
}


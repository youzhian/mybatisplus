package com.wofang.mybatisplus.model;

import com.wofang.mybatisplus.util.Constants;

/**
 * k计划消息主体
 * @author 游志安
 */
public class KOrderMsg {
    /**
     * 收货地省份code
     */
    private String province;
    /**
     * 城市code
     */
    private String cityCode;
    /**
     * 区县code
     */
    private String countyCode;
    /**
     * 用户详细地址
     */
    private String userAddr;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 商品ID
     */
    private String productCode;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 发展人ID
     */
    private String developerId;
    /**
     * 发展人名称
     */
    private String developerName;
    /**
     * 触点ID
     */
    private String touchId;
    /**
     * 项目ID
     */
    private String projectId;
    /**
     * 客户信息
     */
    private CustomInfo customInfo;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getTouchId() {
        return touchId;
    }

    public void setTouchId(String touchId) {
        this.touchId = touchId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public CustomInfo getCustomInfo() {
        return customInfo;
    }

    public void setCustomInfo(CustomInfo customInfo) {
        this.customInfo = customInfo;
    }

    /**
     * 初始化常量参数
     * @return
     */
    public static KOrderMsg init(){
        KOrderMsg ko = new KOrderMsg();
        ko.setProductId(Constants.PRODUCT_PRODUCT_ID);
        ko.setProductCode(Constants.PRODUCT_PRODUCT_CODE);
        ko.setProductName(Constants.PRODUCT_PRODUCT_NAME);
        ko.setTouchId(Constants.PRODUCT_TOUCH_ID);
        ko.setProjectId(Constants.PRODUCT_PROJECT_ID);
        ko.setDeveloperId(Constants.PRODUCT_DEVELOPER_ID);
        ko.setDeveloperName(Constants.PRODUCT_DEVELOPER_NAME);
        return ko;
    }
}

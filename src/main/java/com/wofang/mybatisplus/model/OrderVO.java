package com.wofang.mybatisplus.model;

/**
 * 表单提交类，用于接收前端传递的表单的值
 * @author 游志安
 */
public class OrderVO {
    /**
     * 证件号
     */
    private String certId;
    /**
     * 证件人名称
     */
    private String certName;
    /**
     * 联系电话
     */
    private String custPhone;
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
     * 获取 证件号
     *
     * @return certId 证件号
     */
    public String getCertId() {
        return this.certId;
    }

    /**
     * 设置 证件号
     *
     * @param certId 证件号
     */
    public void setCertId(String certId) {
        this.certId = certId;
    }

    /**
     * 获取 证件人名称
     *
     * @return certName 证件人名称
     */
    public String getCertName() {
        return this.certName;
    }

    /**
     * 设置 证件人名称
     *
     * @param certName 证件人名称
     */
    public void setCertName(String certName) {
        this.certName = certName;
    }

    /**
     * 获取 联系电话
     *
     * @return custPhone 联系电话
     */
    public String getCustPhone() {
        return this.custPhone;
    }

    /**
     * 设置 联系电话
     *
     * @param custPhone 联系电话
     */
    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    /**
     * 获取 收货地省份code
     *
     * @return province 收货地省份code
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * 设置 收货地省份code
     *
     * @param province 收货地省份code
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取 城市code
     *
     * @return cityCode 城市code
     */
    public String getCityCode() {
        return this.cityCode;
    }

    /**
     * 设置 城市code
     *
     * @param cityCode 城市code
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取 区县code
     *
     * @return countyCode 区县code
     */
    public String getCountyCode() {
        return this.countyCode;
    }

    /**
     * 设置 区县code
     *
     * @param countyCode 区县code
     */
    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    /**
     * 获取 用户详细地址
     *
     * @return userAddr 用户详细地址
     */
    public String getUserAddr() {
        return this.userAddr;
    }

    /**
     * 设置 用户详细地址
     *
     * @param userAddr 用户详细地址
     */
    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }
}

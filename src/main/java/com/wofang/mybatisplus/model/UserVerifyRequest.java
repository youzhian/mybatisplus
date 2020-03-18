package com.wofang.mybatisplus.model;

public class UserVerifyRequest {

    /**
     * 证件号
     */
    private String CertNum;
    /**
     * 证件姓名
     */
    private String CustName;


    /**
     * 获取 证件号
     *
     * @return CertNum 证件号
     */
    public String getCertNum() {
        return this.CertNum;
    }

    /**
     * 设置 证件号
     *
     * @param CertNum 证件号
     */
    public void setCertNum(String CertNum) {
        this.CertNum = CertNum;
    }

    /**
     * 获取 证件姓名
     *
     * @return CustName 证件姓名
     */
    public String getCustName() {
        return this.CustName;
    }

    /**
     * 设置 证件姓名
     *
     * @param CustName 证件姓名
     */
    public void setCustName(String CustName) {
        this.CustName = CustName;
    }
}

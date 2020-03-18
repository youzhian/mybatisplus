package com.wofang.mybatisplus.model;

public class CustomInfo {
    /**
     * 证件号
     */
    private String certId;
    /**
     * 证件人名称
     */
    private String certName;
    /**
     * 证件类型
     */
    private String certType;
    /**
     * 联系电话
     */
    private String custPhone;

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
}

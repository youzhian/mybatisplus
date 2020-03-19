package com.wofang.mybatisplus.model;

public class RequestObj {

    private Object msg;

    private long timestamp;

    private String sign;

    private String uid;

    private String busiSerial;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBusiSerial() {
        return busiSerial;
    }

    public void setBusiSerial(String busiSerial) {
        this.busiSerial = busiSerial;
    }
}

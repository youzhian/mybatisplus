package com.wofang.mybatisplus.model;

/**
 * k计划发送对象
 * @author 游志安
 */
public class Korder {
    /**
     * 消息体
     */
    private KOrderMsg msg;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 用户ID
     */
    private String uid;
    /**
     * 交易流水号
     */
    private String busiSerial;

    public KOrderMsg getMsg() {
        return msg;
    }

    public void setMsg(KOrderMsg msg) {
        this.msg = msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

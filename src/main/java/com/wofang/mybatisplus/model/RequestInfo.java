package com.wofang.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("request_info")
public class RequestInfo {
    /**
     * 物理主键
     */
    @TableId(value="id",type = IdType.AUTO)
    private Long id;
    /**
     * 交易流水号
     */
    @TableField("busi_serial_no")
    private String busiSerialNo;
    /**
     * 请求类型，1为资格验证，2为表单提交
     */
    @TableField("request_type")
    private String requestType;
    /**
     * 请求数据，未加密前的数据
     */
    @TableField("request_data")
    private String requestData;
    /**
     * 请求地址，即加密后最终的请求地址
     */
    @TableField("request_url")
    private String requestUrl;
    /**
     * 响应结果
     */
    @TableField("response_result")
    private String responseResult;
    /**
     * 是否成功标识，1标识成功，2标识失败
     */
    @TableField("success_flg")
    private String successFlg;

    /**
     * 获取 物理主键
     *
     * @return id 物理主键
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置 物理主键
     *
     * @param id 物理主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 交易流水号
     *
     * @return busiSerialNo 交易流水号
     */
    public String getBusiSerialNo() {
        return this.busiSerialNo;
    }

    /**
     * 设置 交易流水号
     *
     * @param busiSerialNo 交易流水号
     */
    public void setBusiSerialNo(String busiSerialNo) {
        this.busiSerialNo = busiSerialNo;
    }

    /**
     * 获取 请求类型，1为资格验证，2为表单提交
     *
     * @return requestType 请求类型，1为资格验证，2为表单提交
     */
    public String getRequestType() {
        return this.requestType;
    }

    /**
     * 设置 请求类型，1为资格验证，2为表单提交
     *
     * @param requestType 请求类型，1为资格验证，2为表单提交
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * 获取 请求数据，未加密前的数据
     *
     * @return requestData 请求数据，未加密前的数据
     */
    public String getRequestData() {
        return this.requestData;
    }

    /**
     * 设置 请求数据，未加密前的数据
     *
     * @param requestData 请求数据，未加密前的数据
     */
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    /**
     * 获取 请求地址，即加密后最终的请求地址
     *
     * @return requestUrl 请求地址，即加密后最终的请求地址
     */
    public String getRequestUrl() {
        return this.requestUrl;
    }

    /**
     * 设置 请求地址，即加密后最终的请求地址
     *
     * @param requestUrl 请求地址，即加密后最终的请求地址
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * 获取 响应结果
     *
     * @return responseResult 响应结果
     */
    public String getResponseResult() {
        return this.responseResult;
    }

    /**
     * 设置 响应结果
     *
     * @param responseResult 响应结果
     */
    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult;
    }

    /**
     * 获取 是否成功标识，1标识成功，2标识失败
     *
     * @return successFlg 是否成功标识，1标识成功，2标识失败
     */
    public String getSuccessFlg() {
        return this.successFlg;
    }

    /**
     * 设置 是否成功标识，1标识成功，2标识失败
     *
     * @param successFlg 是否成功标识，1标识成功，2标识失败
     */
    public void setSuccessFlg(String successFlg) {
        this.successFlg = successFlg;
    }
}

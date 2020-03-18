package com.wofang.mybatisplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.wofang.mybatisplus.model.*;
import com.wofang.mybatisplus.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

/**
 * 大王卡controller
 */
@RestController
@RequestMapping("kingOrder")
public class KingOrderController {
    /**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(KingOrderController.class);
    /**
     * 验证客户是否有资格参与大王卡计划
     * @param certNum 证件号
     * @param custName 证件号客户姓名
     * @return
     */
    @RequestMapping("userVerify")
    public Object userVerify(String certNum,String custName){

        if(StringUtils.isNotBlank(certNum) && StringUtils.isNotBlank(custName)){
            try {
                //请求信息主体
                UserVerifyRequest request = new UserVerifyRequest();
                request.setCertNum(certNum);
                request.setCustName(custName);
                //请求消息
                UserVerifyMsg userVerifyMsg = new UserVerifyMsg();
                userVerifyMsg.setRequest(request);

                String timestamp = DateUtil.getCurrentDateTimeStr();
                String busiSerial = Constants.uid+timestamp;

                RequestObj requestObj = new RequestObj();

                requestObj.setMsg(userVerifyMsg);

                requestObj.setTimestamp(timestamp);
                requestObj.setUid(Constants.uid);
                requestObj.setBusiSerial(busiSerial);

                Map<String,Object> params = new HashMap<>();

                params.put("msg",userVerifyMsg);
                params.put("timestamp",timestamp);
                params.put("uid",Constants.uid);
                params.put("busiSerial",busiSerial);
                //获取签名
                String sign = MD5Utils.createSign(Constants.CHARACTER_ENCODING_UTF8,params,Constants.KEY,Constants.KEY_NAME,true);

                requestObj.setSign(sign);
                log.info("========="+JSONObject.toJSONString(requestObj));
                /**
                 * 加密
                 */
                String key = AESUtil.encrypt(JSONObject.toJSONString(requestObj),Constants.KEY);
                StringBuffer url = new StringBuffer();
                url.append(Constants.URL_USER_VERIFY).append("?").append("uid=").append(Constants.uid).append("&key=").append(key).append("&bs64=0");
                log.info("==============url["+url+"]=================");
                String result = HttpClientUtil.doGet(url.toString());
                log.info(result);
                return result;
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }

        }

        return null;
    }

    /**
     * 用户提交表单提交
     * @return
     */
    @RequestMapping("orderSubmit")
    public Object orderSubmit(OrderVO orderVO){

        //组装客户信息对象
        CustomInfo customInfo = new CustomInfo();
        customInfo.setCertId(orderVO.getCertId());
        customInfo.setCertName(orderVO.getCertName());
        customInfo.setCertType(Constants.CERT_TYPE_ID);
        customInfo.setCustPhone(orderVO.getCustPhone());
        //组装消息体
        KOrderMsg msg = KOrderMsg.init();
        msg.setProvince(orderVO.getProvince());
        msg.setCityCode(orderVO.getCityCode());
        msg.setCountyCode(orderVO.getCountyCode());
        msg.setUserAddr(orderVO.getUserAddr());
        msg.setCustomInfo(customInfo);

        //时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());
        //交易流水
        String busiSerial = UUID.randomUUID().toString();

        //组装表单对象
        Korder order = new Korder();
        order.setMsg(msg);
        order.setUid(Constants.uid);
        order.setTimestamp(timestamp);
        order.setBusiSerial(busiSerial);
        log.info(JSONObject.toJSONString(order));
        String key = null;
        try {
            key = AESUtil.encrypt(JSONObject.toJSONString(order), Constants.KEY);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        StringBuffer url = new StringBuffer();
        url.append(Constants.URL_USER_VERIFY).append("?").append("uid=").append(Constants.uid).append("&key=").append(key).append("&bs64=0");
        log.info("==============url["+url+"]=================");
        String result = HttpClientUtil.doGet(url.toString());
        log.info(result);
        return result;
    }
    @RequestMapping("createBusinessNo")
    public Object createBusinessNo(){
        JSONObject json = new JSONObject();
        String businessNo = BusinessUtil.getBusinessNo(Constants.uid,DateUtil.getCurrentDateTimeStr());
        json.put("businessNo",businessNo);
        Timestamp timestamp = DateUtil.now_Timestamp();
        System.out.println((new Date()).getTime());
        System.out.println(System.currentTimeMillis());
        System.out.println(businessNo);
        System.out.println(UUID.randomUUID());

        return json;
    }
}

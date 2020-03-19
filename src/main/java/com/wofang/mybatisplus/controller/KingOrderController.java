package com.wofang.mybatisplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.wofang.mybatisplus.model.*;
import com.wofang.mybatisplus.service.ProvinceCityCountyService;
import com.wofang.mybatisplus.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.net.URLEncoder;
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
     * 省市县service
     */
    @Autowired
    private ProvinceCityCountyService provinceCityCountyService;

    @RequestMapping("apiAddrss")
    public Object apiAddrss() throws Exception {
        JSONObject json = new JSONObject();
        json.put("developerNumber",Constants.PRODUCT_DEVELOPER_ID);

        String busiSerial = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();
        RequestObj obj = new RequestObj();
        obj.setMsg(json);
        obj.setBusiSerial(busiSerial);
        obj.setUid(Constants.uid);
        obj.setTimestamp(timestamp);
        JSONObject p = JSONObject.parseObject(JSONObject.toJSONString(obj));

        Map<String,Object> param = new HashMap<>();
        param.put("msg",obj.getMsg());
        param.put("busiSerial",obj.getBusiSerial());
        param.put("uid",obj.getUid());
        param.put("timestamp",obj.getTimestamp());
        String sign = MD5Utils.createSign(Constants.CHARACTER_ENCODING_UTF8,p,Constants.KEY,Constants.KEY_NAME,true);
        obj.setSign(sign);

        log.info(JSONObject.toJSONString(obj));
        String key = AESUtil.encrypt(JSONObject.toJSONString(obj),Constants.KEY);
        StringBuffer url = new StringBuffer();
        url.append("http://73110010.com/apiAddrss").append("?").append("uid=").append(Constants.uid).append("&key=").append(key).append("&bs64=0");
        log.info("==============url["+url+"]=================");
        String result = HttpClientUtil.doGet(url.toString());
        log.info(result);
        return result;
    }
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
                JSONObject request = new JSONObject();
                request.put("CertNum",certNum);
                request.put("CustName",custName);
                //请求消息
                JSONObject userVerifyMsg = new JSONObject();
                userVerifyMsg.put("Request",request);
                //获取到秒的时间戳
                long timestamp = DateUtil.getcurrentTimeSec();
                //交易流水号
                String busiSerial = Constants.uid+timestamp;

                RequestObj requestObj = new RequestObj();

                requestObj.setMsg(userVerifyMsg);

                requestObj.setTimestamp(timestamp);
                requestObj.setUid(Constants.uid);
                requestObj.setBusiSerial(busiSerial);

                JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(requestObj));

                //获取签名
                String sign = MD5Utils.createSign2(Constants.CHARACTER_ENCODING_UTF8,param,Constants.KEY,Constants.KEY_NAME,true);

                requestObj.setSign(sign);
                log.info("========="+JSONObject.toJSONString(requestObj));
                /**
                 * 加密
                 */
                String key = AESPlus.encrypt(JSONObject.toJSONString(requestObj),Constants.KEY);
                key = URLEncoder.encode(key,Constants.CHARACTER_ENCODING_UTF8);
                StringBuffer url = new StringBuffer();
                url.append(Constants.URL_USER_VERIFY).append("?").append("uid=").append(Constants.uid)
                        .append("&key=").append(key).append("&bs64=0");
                log.info("==============url["+url+"]=================");
                String result = HttpClientUtil.doGet(url.toString());
                log.info(result);
                return result;
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                ResponseUtil.error("系统繁忙，请重试");
            }
        }

        return ResponseUtil.error("参数错误，请重试");
    }

    /**
     * 用户提交表单提交
     * @return
     */
    @RequestMapping("orderSubmit")
    public Object orderSubmit(OrderVO orderVO){
        try {
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
            JSONObject mg = JSONObject.parseObject(JSONObject.toJSONString(msg));

            //时间戳
            long timestamp = DateUtil.getcurrentTimeSec();
            //交易流水
            String busiSerial = UUID.randomUUID().toString();

            //组装表单对象
            RequestObj order = new RequestObj();
            order.setMsg(mg);
            order.setUid(Constants.uid);
            order.setTimestamp(timestamp);
            order.setBusiSerial(busiSerial);
            log.info(JSONObject.toJSONString(order));
            JSONObject od = JSONObject.parseObject(JSONObject.toJSONString(order));
            //生成签名
            String sing = MD5Utils.createSign2(Constants.CHARACTER_ENCODING_UTF8,od,Constants.KEY,Constants.KEY_NAME,true);
            order.setSign(sing);

            String key = null;
            key = AESPlus.encrypt(JSONObject.toJSONString(order), Constants.KEY);
            key = URLEncoder.encode(key, Constants.CHARACTER_ENCODING_UTF8);

            StringBuilder url = new StringBuilder();
            url.append(Constants.URL_USER_VERIFY).append("?").append("uid=").append(Constants.uid);
            url.append("&key=").append(key).append("&bs64=0");
            log.info("==============url[" + url + "]=================");
            String result = HttpClientUtil.doGet(url.toString());
            log.info(result);
            return result;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ResponseUtil.error("系统繁忙，请重新提交");
        }
    }
}

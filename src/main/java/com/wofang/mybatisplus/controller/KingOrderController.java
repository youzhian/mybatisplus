package com.wofang.mybatisplus.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wofang.mybatisplus.model.*;
import com.wofang.mybatisplus.service.ProvinceCityCountyService;
import com.wofang.mybatisplus.service.RequestInfoService;
import com.wofang.mybatisplus.util.*;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
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
    /**
     * 请求信息保存
     */
    @Autowired
    private RequestInfoService requestInfoService;

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
        String requestData = JSONObject.toJSONString(obj);
        log.info(requestData);
        String key = AESUtil.encrypt(requestData,Constants.KEY);
        StringBuffer url = new StringBuffer();
        url.append("http://73110010.com/apiAddrss").append("?").append("uid=").append(Constants.uid).append("&key=").append(key).append("&bs64=0");
        log.info("==============url["+url+"]=================");
        String result = HttpClientUtil.doGet(url.toString());
        log.info(result);

        return result;
    }
    /**
     * 验证客户是否有资格参与大王卡计划
     * @param contactPhone 联系电话
     * @param certId 证件号
     * @return
     */
    @RequestMapping("userVerify")
    public JSONObject userVerify(String certId,String contactPhone){

        if(StringUtils.isNotBlank(contactPhone) && StringUtils.isNotBlank(certId)){
            //请求参数
            RequestInfo requestInfo = new RequestInfo();
            try {
                //请求消息
                JSONObject userVerifyMsg = new JSONObject();
                userVerifyMsg.put("contactPhone",contactPhone);
                userVerifyMsg.put("certId",certId);
                userVerifyMsg.put("goodsId",Constants.PRODUCT_PRODUCT_ID);

                //获取到秒的时间戳
                long timestamp = DateUtil.getcurrentTimeSec();
                //交易流水号
                String busiSerial = BusinessUtil.getBusinessSerialNo(Constants.uid,timestamp);

                RequestObj requestObj = new RequestObj();

                requestObj.setMsg(userVerifyMsg);

                requestObj.setTimestamp(timestamp);
                requestObj.setUid(Constants.uid);
                requestObj.setBusiSerial(busiSerial);

                JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(requestObj));

                //获取签名
                String sign = MD5Utils.createSign2(Constants.CHARACTER_ENCODING_UTF8,param,Constants.KEY,Constants.KEY_NAME,true);

                requestObj.setSign(sign);
                String requestData = JSONObject.toJSONString(requestObj);
                log.info("========="+requestData);
                /**
                 * 加密
                 */
                String key = AESPlus.encrypt(requestData,Constants.KEY);
                key = URLEncoder.encode(key,Constants.CHARACTER_ENCODING_UTF8);
                StringBuffer url = new StringBuffer();
                url.append(Constants.URL_USER_VERIFY).append("?").append("uid=").append(Constants.uid)
                        .append("&key=").append(key).append("&bs64=0");
                log.info("==============url["+url.toString()+"]=================");
                String result = HttpClientUtil.doGet(url.toString());
                log.info(result);
                //组装请求信息
                requestInfo.setBusiSerialNo(busiSerial);
                requestInfo.setRequestData(requestData);
                requestInfo.setRequestUrl(url.toString());
                requestInfo.setResponseResult(result);
                requestInfo.setRequestType(Constants.REQUEST_TYPE_VERIFY);
                //默认是失败
                requestInfo.setSuccessFlg(Constants.SUCCESS_FLG_FAIL);

                if(StringUtils.isNotBlank(result)){
                    JSONObject resultJson = JSONObject.parseObject(result);
                    //成功
                    if(Constants.KING_ORDER_SUCCESS_CODE
                            .equals(resultJson.getString(Constants.KING_ORDER_RESP_CODE_KEY))){
                        if(resultJson.containsKey("result")
                                && resultJson.getJSONObject("result").containsKey("body")){
                            String resultCode = resultJson.getJSONObject("result").getJSONObject("body").getString("resultCode");
                            if(Constants.KING_ORDER_SUCCESS_CODE
                                    .equals(resultCode)){
                                //请求成功
                                requestInfo.setSuccessFlg(Constants.SUCCESS_FLG_SUCCESS);
                                return ResponseUtil.success("资格校验通过");
                            }else{
                                return ResponseUtil.success(resultJson.getJSONObject("result").getJSONObject("body")
                                        .getString("resultDesc"));
                            }
                        }

                    }else{
                        resultJson.getString("respDesc");
                        String message = resultJson.getString("respDesc");
                        /*if("9999".equals(resultJson.getString(Constants.KING_ORDER_RESP_CODE_KEY))){
                            message = "身份证号码不合法";
                        }*/
                        return ResponseUtil.error(message);
                    }
                }
                return ResponseUtil.error("系统繁忙，请重试");
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return ResponseUtil.error("系统繁忙，请重试");
            }finally {
                //保存请求信息
                if(StringUtils.isNotBlank(requestInfo.getBusiSerialNo())){
                    try {
                        requestInfoService.save(requestInfo);
                    }catch (Exception e){
                        log.info("插入请求信息异常,请求对象:", JSONObject.toJSONString(requestInfo));
                        log.info("请求对象:");
                        log.error(e.getMessage(),e);
                    }
                }
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
        //请求信息对象
        RequestInfo requestInfo = new RequestInfo();
        try {
            if(StringUtils.isBlank(orderVO.getCertName())){
                return ResponseUtil.error("姓名不能为空");
            }
            if(StringUtils.isBlank(orderVO.getCertId())){
                return ResponseUtil.error("身份证号不能为空");
            }
            if(StringUtils.isBlank(orderVO.getCustPhone())){
                return ResponseUtil.error("联系电话不能为空");
            }
            if(StringUtils.isBlank(orderVO.getProvince()) || StringUtils.isBlank(orderVO.getCityCode())
                    || StringUtils.isBlank(orderVO.getCountyCode())){
                return ResponseUtil.error("所在地不能为空");
            }
            if(StringUtils.isBlank(orderVO.getUserAddr())){
                return ResponseUtil.error("详细地址不能为空");
            }
            //验证资格
            JSONObject verify = userVerify(orderVO.getCertId(),orderVO.getCustPhone());
            //验证不通过
            if(!String.valueOf(ResponseUtil.SUCCESS_CODE).equals(verify.getString("code"))){
                return verify;
            }
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

            String proKey = "";
            if(StringUtils.isNotBlank(orderVO.getSerialNumber())){

                if(StringUtils.isBlank(orderVO.getOwnerProvince())
                        || StringUtils.isBlank(orderVO.getOwnerCityCode())){
                    return ResponseUtil.error("请选择号码归属地");
                }
                //生成抢占资源key,由99999+随机数组成，共16位正整数
                proKey = "99999"+BusinessUtil.getRandomCodeStr(11);
                //占号接口
                boolean state =  changeNumState(orderVO.getOwnerProvince(),orderVO.getCityCode(),proKey,orderVO.getSerialNumber());
                if(!state){
                    return ResponseUtil.error("预选号码失败,请重新选择");
                }
                //是否选号，1为是
                mg.put("is_selected_number","1");
                mg.put("proKey",proKey);
                mg.put("proType","268");
                mg.put("numberProvince",orderVO.getOwnerProvince());
                mg.put("numberCity",orderVO.getOwnerCityCode());
                mg.put("serialNumber",orderVO.getSerialNumber());

            }

            //时间戳
            long timestamp = DateUtil.getcurrentTimeSec();
            //交易流水
            String busiSerial = BusinessUtil.getBusinessSerialNo(Constants.uid,timestamp);

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
            url.append(Constants.URL_KING_ORDER_SUBMIT).append("?").append("uid=").append(Constants.uid);
            url.append("&key=").append(key).append("&bs64=0");
            log.info("==============url[" + url + "]=================");
            String result = HttpClientUtil.doGet(url.toString());
            log.info(result);
            //组装请求数据
            requestInfo.setBusiSerialNo(busiSerial);
            requestInfo.setRequestType(Constants.REQUEST_TYPE_SUBMIT);
            requestInfo.setRequestUrl(url.toString());
            requestInfo.setResponseResult(result);
            requestInfo.setRequestData(JSONObject.toJSONString(order));
            requestInfo.setSuccessFlg(Constants.SUCCESS_FLG_FAIL);

            if(StringUtils.isNotBlank(result)){
                JSONObject resultJson = JSONObject.parseObject(result);
                //调用成功
                if(Constants.KING_ORDER_SUCCESS_CODE
                        .equals(resultJson.getString(Constants.KING_ORDER_RESP_CODE_KEY))){
                    if(resultJson.containsKey("result")){
                        JSONObject rj = resultJson.getJSONObject("result");
                        if(rj != null && rj.containsKey("body") && rj.getJSONObject("body").containsKey("root")){
                            //返回码
                            String respCode = rj.getJSONObject("body").getJSONObject("root")
                                    .getString(Constants.KING_ORDER_RESP_CODE_KEY);
                            //表示成功
                            if("suc".equals(respCode)){
                                //请求成功
                                requestInfo.setSuccessFlg(Constants.SUCCESS_FLG_SUCCESS);
                                return ResponseUtil.success("订单提交成功");
                            }
                        }
                    }
                    return ResponseUtil.success("系统繁忙，请重新提交");
                }else{
                    String message = "系统繁忙，请重新提交";

                    return ResponseUtil.error(message);
                }
            }
            return ResponseUtil.error("系统繁忙，请重新提交");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ResponseUtil.error("系统繁忙，请重新提交");
        }finally {
            //保存请求信息
            if(StringUtils.isNotBlank(requestInfo.getBusiSerialNo())){
                try {
                    requestInfoService.save(requestInfo);
                }catch (Exception e){
                    log.info("插入请求信息异常,请求对象:", JSONObject.toJSONString(requestInfo));
                    log.info("请求对象:");
                    log.error(e.getMessage(),e);
                }
            }
        }
    }

    /**
     * 查询可选号码
     * @param provinceCode 归属地省份
     * @param cityCode 归属地城市
     * @param searchValue 尾数
     * @return
     */
    @RequestMapping("querySelectNumber")
    public Object querySelectNumber(String provinceCode,String cityCode,String searchValue){
        if(StringUtils.isBlank(provinceCode) || StringUtils.isBlank(cityCode)){
            return ResponseUtil.error("请选择归属地");
        }
        /*List<String> nums2 = new ArrayList<>();
        for(int i=0;i<25;i++){
            nums2.add(BusinessUtil.getRandomCodeStr(11));
        }
        nums2 = BusinessUtil.getRandomNumberByMaxLength(nums2,25);
        if(true)
            return ResponseUtil.success("成功",nums2);*/
        //请求地址
        String url = "https://www.73110010.com/portal/simpleWoSale/query/selectNumber";

        JSONObject request = new JSONObject();

        JSONObject msg = new JSONObject();
        msg.put("provinceCode",provinceCode);
        msg.put("cityCode",cityCode);
        msg.put("goodsId",Constants.PRODUCT_PRODUCT_ID);
        if(StringUtils.isNotBlank(searchValue)){
            msg.put("searchCategory","3");
            msg.put("searchType","02");
            msg.put("searchValue",searchValue);
        }
        //时间戳
        long timestamp = DateUtil.getcurrentTimeSec();
        //交易流水
        String busiSerial = BusinessUtil.getBusinessSerialNo(Constants.uid,timestamp);
        request.put("msg",msg);
        request.put("timestamp",timestamp);
        request.put("uid",Constants.uid);
        request.put("busiSerial",busiSerial);

        //生成签名
        String sing = MD5Utils.createSign2(Constants.CHARACTER_ENCODING_UTF8,request,Constants.KEY,Constants.KEY_NAME,true);
        request.put("sign",sing);
        log.info(request.toJSONString());
        String key = null;
        try {
            key = AESPlus.encrypt(request.toJSONString(), Constants.KEY);
            key = URLEncoder.encode(key, Constants.CHARACTER_ENCODING_UTF8);
            StringBuilder urlSb = new StringBuilder();
            urlSb.append(url).append("?").append("uid=").append(Constants.uid);
            urlSb.append("&key=").append(key).append("&bs64=0");
            log.info("==============url[" + url + "]=================");
            String result = HttpClientUtil.doGet(url.toString());
            log.info(result);
            if(StringUtils.isNotBlank(result)){
                JSONObject resultJSON = JSONObject.parseObject(result);
                if(resultJSON.containsKey("result") && resultJSON.getJSONObject("result").containsKey("body")){
                    if(resultJSON.getJSONObject("result").getJSONObject("body").containsKey("numList")){
                        JSONArray numList = resultJSON.getJSONObject("result").getJSONObject("body").getJSONArray("numList");
                        if(numList.isEmpty()){
                            return ResponseUtil.success("未查询到符合条件的号码");
                        }
                        //手机号集合
                        List<String> nums = new ArrayList<>();
                        for(int i=0;i < numList.size();i++){
                            JSONObject jo = numList.getJSONObject(i);
                            nums.add(jo.getString("serialNumber"));
                        }
                        if(!nums.isEmpty()){
                            BusinessUtil.getRandomNumberByMaxLength(nums,24);
                            return ResponseUtil.success("查询成功",nums);
                        }
                    }
                    return ResponseUtil.success("未查询到符合条件的号码");
                }
            }
            return ResponseUtil.error("查询失败,请再次尝试");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
            return ResponseUtil.error("系统繁忙，请再次尝试");
        }

    }

    /**
     * 选占号码，不做参数校验
     * @param provinceCode 归属地身份
     * @param cityCode 归属地城市
     * @param proKey 资源预占关键字 要与表单提交时的一致
     * @param serialNumber 预占手机号
     * @return true 表示成功，false 表示失败
     */
    public boolean changeNumState(String provinceCode,String cityCode,String proKey,String serialNumber){
        String url = "https://www.73110010.com/portal/simpleWoSale/order/changeNumState";

        JSONObject msg = new JSONObject();
        msg.put("provinceCode",provinceCode);
        msg.put("cityCode",cityCode);
        msg.put("proKey",serialNumber);

        JSONObject request = new JSONObject();
        //时间戳
        long timestamp = DateUtil.getcurrentTimeSec();
        //交易流水
        String busiSerial = BusinessUtil.getBusinessSerialNo(Constants.uid,timestamp);
        request.put("msg",msg);
        request.put("timestamp",timestamp);
        request.put("uid",Constants.uid);
        request.put("busiSerial",busiSerial);

        //生成签名
        String sing = MD5Utils.createSign2(Constants.CHARACTER_ENCODING_UTF8,request,Constants.KEY,Constants.KEY_NAME,true);
        request.put("sign",sing);
        log.info(request.toJSONString());
        String key = null;
        //请求信息对象
        RequestInfo requestInfo = new RequestInfo();
        try {
            key = AESPlus.encrypt(request.toJSONString(), Constants.KEY);
            key = URLEncoder.encode(key, Constants.CHARACTER_ENCODING_UTF8);
            StringBuilder urlSb = new StringBuilder();
            urlSb.append(url).append("?").append("uid=").append(Constants.uid);
            urlSb.append("&key=").append(key).append("&bs64=0");
            log.info("==============url[" + url + "]=================");
            String result = HttpClientUtil.doGet(url.toString());
            log.info(result);

            //组装请求数据
            requestInfo.setBusiSerialNo(busiSerial);
            requestInfo.setRequestType(Constants.REQUEST_TYPE_STATE);
            requestInfo.setRequestUrl(urlSb.toString());
            requestInfo.setResponseResult(result);
            requestInfo.setRequestData(request.toJSONString());
            requestInfo.setSuccessFlg(Constants.SUCCESS_FLG_FAIL);

            if(StringUtils.isNotBlank(result)){
                JSONObject response = JSONObject.parseObject(result);
                if(response.containsKey("result")
                        && response.getJSONObject("result").containsKey("body")){
                    String respCode = response.getJSONObject("result").getJSONObject("body").getString("respCode");
                    if(Constants.KING_ORDER_SUCCESS_CODE.equals(respCode)){
                        requestInfo.setSuccessFlg(Constants.SUCCESS_FLG_SUCCESS);
                        return true;
                    }
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            //保存请求信息
            if(StringUtils.isNotBlank(requestInfo.getBusiSerialNo())){
                try {
                    requestInfoService.save(requestInfo);
                }catch (Exception e){
                    log.info("插入请求信息异常,请求对象:", JSONObject.toJSONString(requestInfo));
                    log.info("请求对象:");
                    log.error(e.getMessage(),e);
                }
            }
        }

        return false;
    }
}

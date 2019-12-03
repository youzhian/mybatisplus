package com.wofang.mybatisplus.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

public class ResponseUtil {

    /**
     * 成功时的标识
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 错误的默认标识
     */
    public static final int DEFAULT_ERROR_CODE = 300;
    /**
     * 成功时默认的提示信息
     */
    private static final String DEFAULT_SUCCESS_MSG = "成功";
    /**
     * 失败时的默认标识
     */
    private static final String DEFAULT_ERROR_MSG = "失败";

    public static Object success(){
        return success(null);
    }

    public static Object success(String message){
        return success(message,null);
    }

    public static Object success(Object data){
        return success(null,data);
    }

    public static Object success(String message,Object data){
        JSONObject result = new JSONObject();
        result.put("code",SUCCESS_CODE);
        result.put("message", StringUtils.isEmpty(message)?DEFAULT_SUCCESS_MSG:message);
        result.put("data",data);

        return result;
    }

    public static Object error(String message){
        return error(null,message);
    }

    public static Object error(Integer code,String message){
        return error(code,message,null);
    }

    /**
     * 根据data返回
     * @param data
     * @return
     */
    public static Object error(Object data){
        return error(null,null,data);
    }

    public static Object error(Integer code,String message,Object data){
        JSONObject result = new JSONObject();
        result.put("code",code == null?DEFAULT_ERROR_CODE:code);
        result.put("message", StringUtils.isEmpty(message)?DEFAULT_ERROR_MSG:message);
        result.put("data",data);

        return result;
    }
}

package com.wofang.mybatisplus.util;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class BusinessUtil {
    /**
     * 计数器
     */
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 获取交易流水号
     * @param uid 用户ID
     * @param timestamp 时间戳
     * @return
     */
    public static String getBusinessSerialNo(String uid,long timestamp){
        StringBuffer buffer = new StringBuffer(uid);
        buffer.append(timestamp).append(UUID.randomUUID().toString());
        return buffer.toString();
    }

    /**
     * 生成交易流水号
     * @param uid
     * @param timestamp
     * @return
     */
    public static String getBusinessNo(String uid,String timestamp){
        //获取当前数量
        int num = atomicInteger.incrementAndGet();
        if(atomicInteger.get() >= Constants.MAX_NUM){
            atomicInteger.set(0);
            num = atomicInteger.incrementAndGet();
        }
        String no = makeUp(String.valueOf(num),10,"0");
        StringBuffer businessNo = new StringBuffer(uid);
        businessNo.append(timestamp).append(no);
        return businessNo.toString();
    }

    /**
     * 根据要求的长度对内容进行补齐
     * @param context 内容，若为空，则返回长度为len的前缀组成的字符串
     * @param len 长度，若长度比内容短，则不做处理
     * @param prefix 前缀，若值为空，则默认补齐0，前缀的长度必须为1
     * @return
     */
    public static String makeUp(String context,int len,String prefix){
        StringBuffer result = new StringBuffer();

        int clen = StringUtils.length(context);

        if(clen > len){
            return context;
        }
        for(int i = 0;i<len-clen;i++){
            result.append(prefix);
        }
        if(StringUtils.isNotBlank(context)){
            result.append(context);
        }
        return result.toString();
    }

    /**
     * 根据对象属性生成map格式的数据
     * @param o
     * @return
     */
    public Map<String,Object> getParamMap(Object o){
        if(o instanceof String){
            return null;
        }
        Map<String,Object> params = new HashMap<>();

        return params;
    }

    /**
     * 返回长度为【strLength】的随机数，在前面补0
     * @param strLength 长度
     * @return 随机数
     */
    public static String getFixLengthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLengthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLengthString.substring(1, strLength + 1);
    }
}

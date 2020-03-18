package com.wofang.mybatisplus.util;



import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.Map.Entry;


/**
 * MD5加密工具类
 * @author Bocn
 * @version 1.0.0
 * @filename MD5Utils.java
 * @time 2017-7-14 上午9:53:53
 * @copyright(C) 2017
 */
public class MD5Utils {
	
	private static String HubKey = "naliwan";
	public static final String Error = "签名失败";
	
	/**
	 * 将map中的数据加密成MD5串
	 * @param characterEncoding		字符格式"UTF-8"
	 * @param parameters			加密map
	 * @param API_KEY				加密密钥，加于末尾，没有可为空
	 * @param flag					是否升序排序 true：是
	 * @return
	 */
	public static String createSign(String characterEncoding, Map<String,Object> parameters, String API_KEY,String key_name, boolean flag){
		
        StringBuffer sb = new StringBuffer();
        Map<String,Object> map = new HashMap<String,Object>();
        map.putAll(parameters);
        Set es = null;
        //map按升序排序
        if(flag){
        	TreeMap<String, Object> treemap = new TreeMap<String, Object>(map);
        	es = treemap.entrySet();
        }else{
        	es = map.entrySet();
        }
        
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Entry entry = (Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.deleteCharAt(sb.length() - 1);//去除最后一个字符
        if(StringUtils.isBlank(API_KEY)){
        	API_KEY = "";
        }
        if(StringUtils.isNotBlank(key_name)
        		&& StringUtils.isNotBlank(API_KEY)){
        	sb.append("&"+key_name+"=" + API_KEY);
        }else{
        	sb.append(API_KEY);
        }
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }


	/**
	 * md5加密串校验(只拼接value)
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
	public static boolean MD5Verifier(String characterEncoding, Map<String,Object> parameters, String md5){
		try {
			String sign = createSign(characterEncoding,parameters);
			if (null != sign && md5.equals(sign)) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public static String createSign(String characterEncoding, Map<String,Object> parameters){
		String sign = "";
		try {
			StringBuffer sb = new StringBuffer();
			Map<String, Object> map = new HashMap<String, Object>();
			map.putAll(parameters);
			Set es = null;
			//map按升序排序
			TreeMap<String, Object> treemap = new TreeMap<String, Object>(map);
			es = treemap.entrySet();
			Iterator it = es.iterator();
			while (it.hasNext()) {
				Entry entry = (Entry) it.next();
				String k = (String) entry.getKey();
				Object v = entry.getValue();
				if (null != v && !"".equals(v)) {
					sb.append(v);
				}
			}
			sb.append(HubKey);
			sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sign ;
		
	}
	
}

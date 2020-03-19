package com.wofang.mybatisplus.util;



import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wofang.mybatisplus.model.UserVerifyMsg;
import com.wofang.mybatisplus.model.UserVerifyRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.origin.SystemEnvironmentOrigin;

import javax.sound.midi.Soundbank;
import java.nio.charset.StandardCharsets;
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
	 * 将map中的数据加密成MD5串
	 * @param characterEncoding		字符格式"UTF-8"
	 * @param parameters			加密map
	 * @param API_KEY				加密密钥，加于末尾，没有可为空
	 * @param flag					是否升序排序 true：是
	 * @return
	 */
	public static String createSign2(String characterEncoding, Map<String,Object> parameters, String API_KEY,String key_name, boolean flag){

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
				sb.append(k).append(v);
			}
		}
		if(StringUtils.isBlank(API_KEY)){
			API_KEY = "";
		}
		if(StringUtils.isNotBlank(key_name)
				&& StringUtils.isNotBlank(API_KEY)){
			sb.append(key_name+API_KEY);
		}else{
			sb.append(API_KEY);
		}
		System.out.println(sb.toString());
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

	public static void main(String [] args){

		JSONObject json =  new JSONObject();
		json.put("busiSerial","gdzx1584584684846");
		json.put("timestamp",1584584684846L);
		json.put("uid","gdzx");
		UserVerifyMsg msg = new UserVerifyMsg();
		UserVerifyRequest req = new UserVerifyRequest();
		req.setCustName("游志安");
		req.setCertNum("460003198805152812");
		msg.setRequest(req);
		JSONObject re = new JSONObject();
		re.put("CustName","游志安");
		re.put("CertNum","460003198805152812");
		JSONObject mg = new JSONObject();
		mg.put("Request",re);
		json.put("msg",msg);
		String sign = MD5Utils.createSign2(null,json,Constants.KEY,Constants.KEY_NAME,true);
		System.out.println(sign);
		json.put("msg",mg);
		sign = MD5Utils.createSign2(StandardCharsets.UTF_8.name(),json,Constants.KEY,Constants.KEY_NAME,true);
		System.out.println(sign);
		System.out.println(mg);
		String s = "busiSerialgdzx1584584684846msg{\"Request\":{\"CustName\":\"游志安\",\"CertNum\":\"460003198805152812\"}}timestamp1584584684846uid\"gdzx\"keygdzx18853289xzdg";
		String s2 = MD5Util.MD5Encode(s, StandardCharsets.UTF_8.name()).toUpperCase();
		System.out.println(s2);
		StringBuffer sb = new StringBuffer();
		sb.append("busiSerial").append("gdzx1584584684846").append("msg").append(re)
				.append("timestamp").append(1584584684846L).append("uid").append("gdzx")
				.append("key").append("gdzx18853289xzdg");
		System.out.println(sb.toString());
		s2 = MD5Util.MD5Encode(sb.toString(), StandardCharsets.UTF_8.name()).toUpperCase();
		System.out.println(s2);
		System.out.println(DateUtil.now_Timestamp().getTime());
		
	}
}

package com.wofang.mybatisplus.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.*;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * Java常用的单向加密的方法 对摘要信息进行加密编码
 * 
 * @author chengys
 */
public final class EncryptUtil {

	/** 16 进制数字 */
	private static final String[] HEX_DIGIT = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	/**
	 * log对象
	 */
	private static final Logger LOG = LogManager.getLogger(EncryptUtil.class);

	/**
	 * 禁止生成实例
	 */
	private EncryptUtil() {

	}

	/**
	 * 将字节数组转换为16进制的字符串
	 * 
	 * @param byteArray 字节数组
	 * @return 16进制的字符串
	 */
	private static String byteArrayToHexString(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (byte byt : byteArray) {
			sb.append(byteToHexString(byt));
		}
		return sb.toString();
	}

	/**
	 * 将字节转换为16进制字符串
	 * 
	 * @param byt 字节
	 * @return 16进制字符串
	 */
	private static String byteToHexString(byte byt) {
		int n = byt;
		if (n < 0) {
			n = 256 + n;
		}
		return HEX_DIGIT[n / 16] + HEX_DIGIT[n % 16];
	}

	/**
	 * 将摘要信息转换为相应的编码
	 * 
	 * @param code 编码类型
	 * @param message 摘要信息
	 * @return 相应的编码字符串
	 * @throws Exception 例外
	 */
	private static String Encode(String code, String message) throws Exception {
		MessageDigest md;
		String encode = null;
		md = MessageDigest.getInstance(code);
		encode = byteArrayToHexString(md.digest(message.getBytes("utf-8")));

		return encode;
	}

	/**
	 * 将摘要信息转换成MD5编码
	 * 
	 * @param message 摘要信息
	 * @return MD5编码之后的字符串
	 * @throws Exception 例外
	 */
	public static String md5Encode(String message) throws Exception {
		return Encode("MD5", message);
	}

	/**
	 * 将摘要信息转换成SHA编码
	 * 
	 * @param message 摘要信息
	 * @return SHA编码之后的字符串
	 * @throws Exception 例外
	 */
	public static String shaEncode(String message) throws Exception {
		return Encode("SHA", message);
	}

	/**
	 * 将摘要信息转换成SHA-256编码
	 * 
	 * @param message 摘要信息
	 * @return SHA-256编码之后的字符串
	 * @throws Exception 例外
	 */
	public static String sha256Encode(String message) throws Exception {
		return Encode("SHA-256", message);
	}

	/**
	 * 将摘要信息转换成SHA-512编码
	 * 
	 * @param message 摘要信息
	 * @return SHA-512编码之后的字符串
	 * @throws Exception 例外
	 */
	public static String sha512Encode(String message) throws Exception {
		return Encode("SHA-512", message);
	}

	/**
	 * 获取加密后的数据<br>
	 * 
	 * @param str 原字符串
	 * @return 返回加密后的数据
	 */
	public static String encrypt(String str) {
		byte[] b = null;
		String s = null;
		if (StringUtils.isBlank(str)) {
			return "";
		}
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			Encoder encoder = Base64.getEncoder();
			s = encoder.encodeToString(b);
		}
		return s;
	}

	/**
	 * 解密,得到加密前的原始数据<br>
	 * 
	 * @param str 被加密后的数据
	 * @return 返回解密后的数据
	 */
	public static String decrypt(String str) {
		byte[] b = null;
		String result = null;
		if (StringUtils.isBlank(str)) {
			return "";
		}
		Decoder decoder = Base64.getDecoder();
		try {
			b = decoder.decode(str);
			result = new String(b, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获得安全展示数据<br>
	 * 
	 * @param map 原数据
	 * @param keysWithIndex 替换字段名、开始、截止索引位置及补齐*个数 ,如：new
	 *        String[]={"name,3,6,0","phone,7,11"}; 或： new
	 *        String[]={"name,3,6","phone,7"};
	 * @return 返回安全数据集合
	 */
	public static Map<String, String> getSafetyData(final Map<String, String> map, final String[] keysWithIndex) {
		Map<String, String> mapClone = new HashMap<String, String>();
		mapClone.putAll(map);
		for (int i = 0; i < keysWithIndex.length; i++) {
			String[] keys = keysWithIndex[i].split(",");
			if (keys.length == 0) {
				continue;
			} else if (keys.length == 2) {
				String tempValue = getSafetyData(mapClone.get(keys[0]), Integer.valueOf(keys[1]), -1);
				mapClone.put(keys[0], tempValue);
			} else if (keys.length == 3) {
				String tempValue = getSafetyData(mapClone.get(keys[0]), Integer.valueOf(keys[1]), Integer.valueOf(keys[2]), -1);
				mapClone.put(keys[0], tempValue);
			} else if (keys.length == 4) {
				String tempValue = getSafetyData(mapClone.get(keys[0]), Integer.valueOf(keys[1]), Integer.valueOf(keys[2]),
				        Integer.valueOf(keys[3]));
				mapClone.put(keys[0], tempValue);
			}
		}
		return mapClone;
	}

	/**
	 * 获得安全展示数据<br>
	 * 
	 * @param list 原数据集合
	 * @param keysWithIndex 替换字段名、开始及截止索引位置 ,如：new
	 *        String[]={"name,3,6,4","phone,7,11"}; 或： new
	 *        String[]={"name,3,6","phone,7"};
	 * @return 返回安全数据集合
	 */
	public static List<Map<String, String>> getSafetyData(final List<Map<String, String>> list, final String[] keysWithIndex) {
		List<Map<String, String>> listClone = new ArrayList<Map<String, String>>();
		for (int i = 0; i < listClone.size(); i++) {
			Map<String, String> mapBean = getSafetyData(list.get(i), keysWithIndex);
			listClone.add(i, mapBean);
		}
		return listClone;
	}

	/**
	 * 获得安全展示数据<br>
	 * 
	 * @param str 原数据
	 * @param beginIndex 替换开始索引位置
	 * @param num 补*个数
	 * @return 返回安全数据
	 */
	public static String getSafetyData(final String str, final int beginIndex, final int num) {
		return EncryptUtil.getSafetyData(str, beginIndex, str.length(), num);
	}

	/**
	 * 获得安全展示数据<br>
	 * 
	 * @param list 原数据集合
	 * @param keysWithIndex 替换字段名、开始及截止索引位置 ,如：new
	 *        String[]={"name,3,6,4","phone,7,11"}; 或： new
	 *        String[]={"name,3,6","phone,7"};
	 * @return 返回安全数据集合
	 */
	public static List<Map<String, String>> getSafetyDataLeftRight(final List<Map<String, String>> list,
	        final String[] keysWithIndex) {
		List<Map<String, String>> listClone = new ArrayList<Map<String, String>>();
		for (int i = 0; i < listClone.size(); i++) {
			Map<String, String> mapBean = getSafetyDataLeftRight(list.get(i), keysWithIndex);
			listClone.add(i, mapBean);
		}
		return listClone;
	}

	/**
	 * 获得安全展示数据<br>
	 * 
	 * @param map 原数据
	 * @param keysWithIndex 替换字段名、开始、截止索引位置及补齐*个数 ,如：new
	 *        String[]={"name,3,6,0","phone,7,11"}; 或： new
	 *        String[]={"name,3,6","phone,7"};
	 * @return 返回安全数据集合
	 */
	public static Map<String, String> getSafetyDataLeftRight(final Map<String, String> map, final String[] keysWithIndex) {
		Map<String, String> mapClone = new HashMap<String, String>();
		mapClone.putAll(map);
		for (int i = 0; i < keysWithIndex.length; i++) {
			String[] keys = keysWithIndex[i].split(",");
			if (keys.length == 0) {
				continue;
			} else if (keys.length == 2) {
				String tempValue = getSafetyData(mapClone.get(keys[0]), Integer.valueOf(keys[1]), -1);
				mapClone.put(keys[0], tempValue);
			} else if (keys.length == 3) {
				String tempValue = getSafetyDataLeftRight(mapClone.get(keys[0]), Integer.valueOf(keys[1]),
				        Integer.valueOf(keys[2]), -1);
				mapClone.put(keys[0], tempValue);
			} else if (keys.length == 4) {
				String tempValue = getSafetyDataLeftRight(mapClone.get(keys[0]), Integer.valueOf(keys[1]),
				        Integer.valueOf(keys[2]), Integer.valueOf(keys[3]));
				mapClone.put(keys[0], tempValue);
			}
		}
		return mapClone;
	}

	/**
	 * 获得安全展示数据<br>
	 * 
	 * @param str 原数据
	 * @param leftIndex 左侧不替换index
	 * @param rightIndex 右侧不替换index
	 * @param num 补齐*个数
	 * @return 返回安全数据
	 */
	public static String getSafetyDataLeftRight(final String str, final int leftIndex, final int rightIndex, final int num) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return getSafetyData(str, leftIndex, str.length() - rightIndex, num);
	}

	/**
	 * 获得安全展示数据<br>
	 * 
	 * @param str 原数据
	 * @param beginIndex 替换开始索引位置
	 * @param endIndex 替换截止索引位置
	 * @param num 补齐*个数
	 * @return 返回安全数据
	 */
	public static String getSafetyData(final String str, final int beginIndex, final int endIndex, final int num) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		int length = str.length();
		int endIndexClone = endIndex;
		int beginIndexClone = beginIndex;
		if (beginIndexClone < 0) {
			beginIndexClone = 0;
		}
		if (beginIndexClone > length) {
			beginIndexClone = length;
		}
		if (endIndexClone > length) {
			endIndexClone = length;
		}
		int numbers = num;
		if (numbers < 0) {
			numbers = endIndexClone - beginIndexClone;
		}
		StringBuffer sBuffer = new StringBuffer();
		if (beginIndexClone > 0) {
			sBuffer.append(str.substring(0, beginIndexClone));
			for (int i = 0; i < numbers; i++) {
				sBuffer.append("*");
			}
		} else {
			for (int i = 0; i < numbers; i++) {
				sBuffer.append("*");
			}
		}
		if (endIndexClone < length - 1) {
			sBuffer.append(StringUtils.substring(str, endIndexClone, str.length()));
		}

		return sBuffer.toString();
	}

	/**
	 * 测试加密及解密<br>
	 * 
	 * @param sourceStr 原文
	 */
	private static void testEncrypt(String sourceStr) {
		long startMili;
		long endMili;
		startMili = System.currentTimeMillis();// 当前时间对应的毫秒数
		String pwd = EncryptUtil.encrypt(sourceStr);
		endMili = System.currentTimeMillis();
		LOG.info("原文：" + sourceStr + ",长度：" + sourceStr.length());
		LOG.info("加密:" + pwd + ",长度：" + pwd.length() + ",加密耗时为：" + (endMili - startMili) + "毫秒");
		String source = EncryptUtil.decrypt(pwd);
		LOG.info("解密：" + source + ",长度：" + source.length() + ",解密耗时为：" + (endMili - startMili) + "毫");
	}

	/**
	 * 
	 * MAIN<br>
	 * 
	 * @param args args
	 * @throws Exception 例外
	 */
	public static void main(String[] args) throws Exception {
		LOG.info("--MD5--:" + EncryptUtil.md5Encode("test"));
		LOG.info("--SHA--:" + EncryptUtil.shaEncode("test"));
		LOG.info("SHA-256:" + EncryptUtil.sha256Encode("test"));
		LOG.info("SHA-512:" + EncryptUtil.sha512Encode("123456"));

		EncryptUtil.testEncrypt("上海市陆家嘴程序员");
		Random ran = new Random();
		EncryptUtil.testEncrypt(ran.nextLong() + "");

		// String phone = "15672317881";
		// String safetyData = EncryptUtil.getSafetyData(phone, 3, 8);
		// LOG.info(safetyData + ",长度：" + safetyData.length());
		// safetyData = EncryptUtil.getSafetyData(phone, 14, 14);
		// LOG.info(safetyData + ",长度：" + safetyData.length());
		// safetyData = EncryptUtil.getSafetyData(phone, 0, 1);
		// LOG.info(safetyData + ",长度：" + safetyData.length());
		// safetyData = EncryptUtil.getSafetyData(phone, 8);
		// LOG.info(safetyData + ",长度：" + safetyData.length());

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "张三");
		map.put("email", "baidu@163.com");
		map.put("phone", "13892779889");
		map.put("cardId", "42168431613138465131");
		map.put("remark", "hello，world");
		map.put("address", "上海市浦东区陆家嘴环路未来资产大厦8F-E座");
		LOG.info("原数据：" + map.toString());

		Map<String, String> map1 = EncryptUtil.getSafetyData(map, new String[] { "name,1,3", "phone,3,7", "cardId,6,16,4",
		        "address,4,18,6" });
		LOG.info("加密后：" + map1.toString());

		LOG.info("原数据：" + map.toString());
		Map<String, String> map2 = EncryptUtil.getSafetyData(map, new String[] { "name,1,3,2", "phone,3,7", "cardId,6,16,4",
		        "address,4,18,6" });
		LOG.info("加密后：" + map2.toString());

		LOG.info("手机号=" + EncryptUtil.decrypt("MTcxMTIzNDEyMzQ="));
		LOG.info("证件号=" + EncryptUtil.decrypt("aHV6aGFvdGVzdA=="));
		LOG.info("【15601600508】加密后的手机号=" + EncryptUtil.encrypt("15601600508"));
	}

}

package com.wofang.mybatisplus.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 功能概要：[功能概要说明]<br>
 *
 * @author 新谓来 [开发者名]
 *
 */
public final class AESUtil {

	/** 禁止实例化 **/
	private AESUtil() {
	}

	/**
	 * 加密
	 * 
	 * @param content 需要加密的内容
	 * @param password 加密密码
	 * @return str 加密过后的字符串(十六进制字符串)
	 * @throws Exception 异常
	 */
	public static final String encrypt(String content, String password) throws Exception {
		String encryptType = "AES";
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		// kgen.init(128, new SecureRandom(password.getBytes()));
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(password.getBytes());
		kgen.init(128, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		byte[] byteContent = content.getBytes("utf-8");
		// 初始化
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] byteresult = cipher.doFinal(byteContent);
		// 转化为十六进制字符串
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteresult.length; i++) {
			String hex = Integer.toHexString(byteresult[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 解密
	 * 
	 * @param content 待解密内容(十六进制字符串)
	 * @param password 解密密钥
	 * @return String 解密后的内容
	 * @throws Exception 异常
	 */
	public static final String decrypt(String content, String password) throws Exception {
		// 将待解密内容转化为二进制
		byte[] byteresult = new byte[content.length() / 2];
		for (int i = 0; i < content.length() / 2; i++) {
			int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2), 16);
			byteresult[i] = (byte) (high * 16 + low);
		}
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		// kgen.init(128, new SecureRandom(password.getBytes()));
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(password.getBytes());
		kgen.init(128, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteresult);
		return new String(result); // 加密
	}

}

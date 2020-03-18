package com.wofang.mybatisplus.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;


/**
 * @description 加密解密工具类
 * 
 */
public class EncryptUtils {
	private static byte SECRETKEY[];
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * @description DES加密
	 * @param key
	 *            密钥
	 * @param data
	 *            明文数据
	 * @return
	 */
	public static String encryptDES(String key, String data) {
		try {
			SECRETKEY = key.getBytes();
			DESKeySpec dks = new DESKeySpec(SECRETKEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);
			IvParameterSpec spec = new IvParameterSpec(SECRETKEY);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, spec);
			byte dest[] = cipher.doFinal(data.getBytes());
			return Base64.encodeBase64String(dest);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @description DES解密
	 * @param key
	 *            密钥
	 * @param data
	 *            密文数据
	 * @return
	 */
	public static String decryptDES(String key, String data) {
		try {
			SECRETKEY = key.getBytes();
			DESKeySpec dks = new DESKeySpec(SECRETKEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);
			IvParameterSpec spec = new IvParameterSpec(SECRETKEY);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, securekey, spec);
			byte buf[] = Base64.decodeBase64(data);
			byte dest[] = cipher.doFinal(buf);
			return new String(dest);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @description MD5加密
	 * @param data
	 *            明文数据
	 * @return
	 */
	public static final String encryptMD5(String data) {
		try {
			byte[] strTemp = data.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(strTemp);
			byte[] md = messageDigest.digest();
			int j = md.length;
			char[] ret = new char[j * 2];
			for (int i = 0, k = 0; i < j; i++) {
				byte byte0 = md[i];
				ret[k++] = hexDigits[byte0 >>> 4 & 0xf];
				ret[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//注册短信最多65字 的编码
	public static final String encryptMD5T16(String data){
		try {
			byte[] strTemp = data.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(strTemp);
			byte[] md = messageDigest.digest();
			int j = md.length;
			char[] ret = new char[j * 2];
			for (int i = 0, k = 0; i < j; i++) {
				byte byte0 = md[i];
				ret[k++] = hexDigits[byte0 >>> 4 & 0xf];
				ret[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(ret).substring(8,24);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String args[]) {
		String data = "127089|5.a24b8385f555445d3898cae50b65f3ef.86400.1295031600-222209506";
		String key = "%*_l5d#@";
		
		String encryptDataDES = EncryptUtils.encryptDES(key,
				data);
		System.out.println("DES加密数据 = " + encryptDataDES);

		String decryptDataDES = EncryptUtils.decryptDES(key,
				encryptDataDES);
		System.out.println("DES解密数据 = " + decryptDataDES);

		String decryptDataMD5 = EncryptUtils.encryptMD5(data);
		System.out.println("MD5加密数据 = " + decryptDataMD5);
		System.out.println(EncryptUtils.encryptMD5T16(data));
		System.out.println(EncryptUtils.encryptMD5T16(data).length());
	}
}

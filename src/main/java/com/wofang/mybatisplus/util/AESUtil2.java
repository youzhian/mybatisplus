package com.wofang.mybatisplus.util;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AESUtil2 {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法、填充模式
     */
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";


    private AESUtil2(){
    }

    /**
     * 加密
     * @param context
     * @return
     * @throws Exception
     */
    public static String encrypt(String context,String password) throws Exception{
        SecretKeySpec key = new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8),ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] cryptogram = cipher.doFinal(context.getBytes());
        return new BASE64Encoder().encode(cryptogram);
    }

    public static String decrypt(String context,String password) throws Exception{
        SecretKeySpec key = new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8),ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(context.getBytes()), StandardCharsets.UTF_8);
    }

}

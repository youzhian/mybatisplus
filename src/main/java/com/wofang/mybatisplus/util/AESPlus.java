package com.wofang.mybatisplus.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESPlus {

    /**
     * 加密函数（明文不进行base64编码）
     *
     * @param input 待加密消息
     * @param key   加密使用的key
     * @return 密文
     */
    public static String encrypt(String input, String key) {
        byte[] cryptogram = null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            cryptogram = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new BASE64Encoder().encode(cryptogram);
    }


    /**
     * 加密函数（明文进行base64编码后再进行加密）
     *
     * @param input 待加密消息
     * @param key   加密使用的key
     * @param bs64  是否需进行base64编码
     * @return 密文
     */
    public static String encrypt(String input, String key, Boolean bs64) throws Exception {
        if (bs64) {
            return encrypt(getBASE64(input), key);
        } else {
            return AESPlus.encrypt(input, key);
        }
    }


    /**
     * 加密函数（明文不进行base64编码）
     *
     * @param input 密文
     * @param key   解密使用的key
     * @return 解密后消息串
     * @throws NoSuchPaddingException    padding异常
     * @throws NoSuchAlgorithmException  解密相关异常
     * @throws IllegalBlockSizeException 解密相关异常
     * @throws BadPaddingException       解密相关异常
     * @throws InvalidKeyException       解密相关异常
     * @throws IOException               解密相关异常
     */
    public static String decrypt(String input, String key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
            byte[] output = null;
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            output = cipher.doFinal(new BASE64Decoder().decodeBuffer(input));
            return new String(output);
    }


    /**
     * @param input 密文
     * @param key   解密使用的key
     * @param bs64  是否对解密后消息进行base64解码
     * @return 解密码后消息
     */
    public static String decrypt(String input, String key, Boolean bs64) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        if (bs64) {
            return getFromBASE64(decrypt(input, key));
        } else {
            return decrypt(input, key);
        }
    }


    /**
     * @param s 输入
     * @return base64
     */
    private static String getBASE64(String s) {
        if (s == null)
            return null;
        return (new BASE64Encoder()).encode(s.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @param s base64消息
     * @return 解码结果
     */
    private static String getFromBASE64(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        //String result = AESPlus.encrypt(msg, "abcde1234567890", true);
        //System.out.println(result);
//        String result="htO7oKqMxB2jxOtEa3/R2dPoHwGwu6Uh0BRForuQrhuJ1ZY4izsUbC52ez2Ko+Azh83fyGGyIJe5AoiQUKkbLJdFbLJa9b0fuQkUrqf2S10JelZhZgNBGZLSQrrNdn+H9xZEKTRj94LsoRzvHz4qHuNKNNGsqyQ1uyXC9Z3jSGLGiqK9p6xN+Pqz/MMBVopoQNbX+ItBcPlUbhTk5NAcRhmOjvlCssSTgiXiyJlhAFQ=";
        String result = "PnV2DZhtJpIQYoIgd6/XFgnkp0TJE4Z2r9vixB1iWwZ6YWj6V+Hxv2IkSDXdMFhtQWyvbcZvHZDB\n" +
                "8LnkqVbbROCv4CFS9SZVuihv5LQMs5GCLwpAloC6b12fd3plbE9WEG/NTidM9TSwHELx4m0odBVz\n" +
                "I88O2amDy9bLQcoMGWS8VY2CFr3o+O5c3Wli3B3kE9HSkB6qSpa/pAsWhOYmnAIm8eHB0AqKLnki\n" +
                "7lEFsbHhwYRx9MJHdtoU55O52AzORgygb+vm7AuJLC2Yas/opdRsWpy5p9HHPb4XZUXfE124/LDU\n" +
                "mEiOnuyWp01kZ8Zb83ss8Gq39xOLfUfvD4Au802POjV8t4wbdyl7hiaSBnMDF9f+InmeDuE41gCm\n" +
                "xOPCMDg6HESkGV4M1A+8wUW+EiLojgBUsVkvr3NSVB6suUcVK9AYZubD0EQ6T4DHZX43CwJ4jFYu\n" +
                "76VdQtuFujgsZIU8+eTQke4BaQ/0mcBmOZiiTmqamE9TZ5iurlwEp85gqpIM8nvZLI1mq6DRUhrY\n" +
                "2lKNz03GLbNY3pgavt6Q/J5YicOvXSkjrArUc/zynccrvGEjMcy/OtkSy+F8sn1PfXheigLo3LlJ\n" +
                "KzBdwqlVJ1WGmiJs1WEk7DgtuxA/Qv8DnfWfsbQ+9weCDDceth6njV8hp1mGHtsqia6uEYFPBClX\n" +
                "Kp/s5Dxy0GPX0A5xjKZjTNPh7UNBJcii2mubcODoxI4JtwVdkfhCuGaF2tOTfUw=";
        System.out.println(AESPlus.decrypt(result, "jinm75830991mnij", false));
    }

}
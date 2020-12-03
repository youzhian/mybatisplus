package com.wofang.mybatisplus.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {
    /**
     * 根据key生成hashCode
     * @param key
     * @return
     */
    public static int getHashCode(String key){
        int hashCode = 0;
        for(int i=0;i<key.length();i++){
            hashCode = hashCode*31+key.charAt(i);
        }
        return hashCode;
    }

    public static String getCardId(String id){
        //校验码
        int[] v = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        //余数码表
        String[] m = new String[]{"1","0","X","9","8","7","6","5","4","3","2"};

        int vcode = 0;
        String [] n = id.split("",id.length());

        for(int i=0;i<id.length();i++){
            int num = Integer.valueOf(n[i]);
            System.out.println("vcode="+vcode+"，n["+i+"]="+num+"，v["+i+"]="+v[i]+",n["+i+"]*v["+i+"]="+num*v[i]);
            vcode += v[i]*num;

        }
        int mod = vcode%11;
        System.out.println("vcode="+vcode+"，m["+mod+"]="+m[mod]);
        return id+m[mod];
    }
    public static boolean isNumber(String str){
        String p = "^[0-9]*$";
        boolean m = Pattern.matches(p,str);
        /*Pattern r = Pattern.compile(p);
        m = r.matcher(str).matches();*/
        return m;
    }
}

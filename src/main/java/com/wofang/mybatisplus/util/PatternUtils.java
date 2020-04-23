package com.wofang.mybatisplus.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {

    public static boolean isNumber(String str){
        String p = "^[0-9]*$";
        boolean m = Pattern.matches(p,str);
        /*Pattern r = Pattern.compile(p);
        m = r.matcher(str).matches();*/
        return m;
    }
}

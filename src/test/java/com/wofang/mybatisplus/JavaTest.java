package com.wofang.mybatisplus;

import org.junit.platform.commons.logging.LoggerFactory;
import sun.awt.SunHints;

import java.util.*;
import java.util.stream.Collectors;

public class JavaTest {

    public static void main(String[] args){
        /*long time1 = part();
        System.out.println("===============");
        long time2 = part2();
        System.out.println(time1+" - "+time2+"="+(time1 - time2));*/
       /* long num = 111;
        List<String> factors = getFactor(num,null);
        factors.forEach(s -> System.out.println(s));*/
       long num1 = 60;
       long num2 = 42;
       long multiple = getMinCommonMultiple(num1,num2);
       System.out.println(num1+" 与 "+num2 + "的最小公倍数为："+multiple);

       long multiple2 = getMinCommonMultiple2(num1,num2);
       System.out.println(num1+" 与 "+num2 + "的最小公倍数为："+multiple2);
    }

    public static long part(){

        int a = 3;
        int b = 5;
        int max = 100000;

        //System.out.println(System.nanoTime());
        List<String> list = new ArrayList<>();
        long nanoTime = System.nanoTime();
        for(int i=6;i<=max;i+=3){
            if(i%b == 0){
                list.add(String.valueOf(i));
            }
        }
        //System.out.println(System.nanoTime());
        //list.forEach(s -> System.out.println(s));
        long nanoTime2 = System.nanoTime();
        //System.out.println(nanoTime2 - nanoTime);
        return nanoTime2 - nanoTime;
    }

    public static long part2(){
        int a = 3;
        int b = 5;
        int max = 100000;
        //System.out.println(System.nanoTime());
        List<String> list = new ArrayList<>();
        long nanoTime = System.nanoTime();
        for(int i=1;i<=max;i++){
            if(i%a == 0 && i%b == 0){
                list.add(String.valueOf(i));
            }
        }
        //System.out.println(System.nanoTime());
        //list.forEach(s -> System.out.println(s));
        long nanoTime2 = System.nanoTime();
        //System.out.println(nanoTime2 - nanoTime);
        return nanoTime2 - nanoTime;
    }

    public static List<String> getFactor(long num,List<String> factors){
        if(factors == null){
            factors = new ArrayList<>();
        }
        if(num < 1){
            return factors;
        }
        if(num == 1){
            factors.add(String.valueOf(num));
            return factors;
        }
        for(long i=2;i<=num;i++){
            if(num%i == 0){
                factors.add(String.valueOf(i));
                getFactor(num/i,factors);
                break;
            }
        }
        return factors;
    }

    /**
     * 根据获取两个数的公共因子得出最小公倍数
     * @param num
     * @param num2
     * @return
     */
    public static long getMinCommonMultiple(long num,long num2){
        if(num < 1 || num2 < 1){
            return -1;
        }
        if(num == num2){
            return num;
        }

        List<String> factor1 = getFactor(num,null);
        List<String> factor2 = getFactor(num2,null);
        Set<String> factor = new HashSet<>();

        long rt = num;
        factor1.forEach(s -> factor.add(s));

        for(String s:factor2){
            if(factor.contains(s)){
                factor.remove(s);
                continue;
            }
            rt *= Long.valueOf(s);
        }

        return rt;
    }

    /**
     * 辗转相除法求两个正整数的最大公约数
     * @param num
     * @param num2
     * @return
     */
    public static long getMaxCommonDivisor(long num,long num2){
        if( num <1 || num2 <1){
            return -1;
        }
        long mod = num % num2;
        if(mod == 0){
           return num2;
        }

        while(mod != 0){
            num = num2;
            num2 = mod;
            mod = num % num2;
        }
        return num2;
    }

    /**
     * 连梁
     * @param num1
     * @param num2
     * @return
     */
    public static long getMaxCommonDivisor2(long num1,long num2){

        while (num1 != num2){
            if(num1>num2){
                num1 -= num2;
            }else{
                num2 -= num1;
            }
        }
        return num1;
    }

    /**
     * 通过获取最大公约数计算两数的最小公倍数
     * @param num
     * @param num2
     * @return
     */
    public static long getMinCommonMultiple2(long num,long num2){
        long maxDivisor = getMaxCommonDivisor2(num,num2);
        System.out.println("maxDivisor = "+maxDivisor);
        return (num * num2)/maxDivisor;
    }
}

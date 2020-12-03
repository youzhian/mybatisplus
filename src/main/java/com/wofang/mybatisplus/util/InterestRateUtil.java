package com.wofang.mybatisplus.util;

import com.baomidou.mybatisplus.extension.api.R;
import com.wofang.mybatisplus.model.RateTable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 利率计算公式
 */
public class InterestRateUtil {

    public static void main(String []args){
        double amount = 75500;
        double rate = 0.076;
        int periodsNum = 36;
        int type = 2;
       List<RateTable> list = InterestRateUtil.createRateTable(amount,rate,periodsNum,type);
        System.out.println("========等额本金还款方式========");
        printRateTables(list);

        list = InterestRateUtil.createRateTable(amount,rate,periodsNum,3);
        System.out.println("========先息后本还款方式========");
        printRateTables(list);

        list = InterestRateUtil.createRateTable(amount,rate,periodsNum,1);
        System.out.println("========等额本息还款方式========");
        printRateTables(list);

    }

    private static void printRateTables(List<RateTable> list){
        if(list != null && !list.isEmpty()){
            System.out.println("期数|还款金额|应还本金|应还利息|剩余本金");

            //总还款利息
            BigDecimal sumInterest = new BigDecimal(0);
            //总本金
            BigDecimal sumPrincipal = new BigDecimal(0);

            for(RateTable r:list){
                StringBuilder sb = new StringBuilder();
                double p = new BigDecimal(r.getInterest()+r.getPrincipal()).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
                sumInterest = sumInterest.add(new BigDecimal(r.getInterest()));
                sumPrincipal = sumPrincipal.add(new BigDecimal(r.getPrincipal()));
                sb.append(r.getPeriods()).append("|")
                        .append(p)
                        .append("|").append(r.getPrincipal()).append("|").append(r.getInterest()).append("|").append(r.getSubPrincipal());
                System.out.println(sb.toString());
            }
            System.out.println("====总还款金额："+sumInterest.add(sumPrincipal).setScale(2,BigDecimal.ROUND_DOWN)
                    +"=========利息总额："+sumInterest.setScale(2,BigDecimal.ROUND_DOWN)
                    +"============实际还款总本金："+sumPrincipal.setScale(2,BigDecimal.ROUND_DOWN));
        }
    }

    /**
     * 创建还款计划表
     * @param amount 借款金额
     * @param rate 贷款年利率
     * @param periodsNum 期数
     * @param type 还款方式，1-等额本息、2-等额本金、3-先息后本
     */
    public static List<RateTable> createRateTable(double amount,double rate,int periodsNum,int type){
        List<RateTable> rateTables = new ArrayList<>();
        //等额本息
        if(type == 1){
            a(amount,rate,periodsNum,rateTables);
        }else if(type == 2){
            //等额本金
            b(amount,rate,periodsNum,rateTables);
        }else if(type == 3){
            //先息后本
            c(amount,rate,periodsNum,rateTables);
        }
        return rateTables;
    }

    /**
     * 等额本息
     * @param amount
     * @param rate
     * @param periodsNum
     * @param rateTables
     */
    private static void a(double amount, double rate, int periodsNum,List<RateTable> rateTables){
        if(amount == 0 || periodsNum == 0){
            System.out.println("=====贷款金额或期数不能为零====");
            return;
        }
        BigDecimal amnt = new BigDecimal(amount);
        //每期利率
        BigDecimal r = new BigDecimal(rate/12);
        BigDecimal one = new BigDecimal(1);

        //每期还款金额：A*r*(1+r)^n/((1+r)^n - 1)，其中a是贷款总额，r是期利率，n是贷款期数
        //因数(1+r)^n
        BigDecimal k = new BigDecimal(Math.pow(one.add(r).doubleValue(),periodsNum));
        //每期还款金额
        BigDecimal principal = amnt.multiply(r).multiply(k).divide(k.subtract(one),2,BigDecimal.ROUND_DOWN);

        for(int i=1;i<=periodsNum;i++){
            RateTable rt = new RateTable();
            //应还利息
            BigDecimal ra = amnt.multiply(r).setScale(2,BigDecimal.ROUND_DOWN);
            //期数
            rt.setPeriods(i);
            //利息
            rt.setInterest(ra.doubleValue());
            //本金
            rt.setPrincipal(principal.subtract(ra).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            amnt = amnt.subtract(new BigDecimal(rt.getPrincipal()));
            //剩余本金
            rt.setSubPrincipal(amnt.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            //最后一期把余数也还了
            if(i == periodsNum){
                rt.setPrincipal(new BigDecimal(rt.getPrincipal()).add(amnt).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                rt.setSubPrincipal(0);
            }
            rateTables.add(rt);
        }

    }

    /**
     * 等额本金
     * @param amount
     * @param rate
     * @param periodsNum
     * @param rateTables
     */
    private static void b(double amount, double rate, int periodsNum,List<RateTable> rateTables){
        BigDecimal amnt = new BigDecimal(amount);

        //每期应还本金
        BigDecimal amt = amnt.divide(new BigDecimal(periodsNum),2,BigDecimal.ROUND_DOWN);

        //每期利率
        BigDecimal r = new BigDecimal(rate/12);
        //余数
        BigDecimal remainder = amnt.subtract(amt.multiply(new BigDecimal(periodsNum)));

        for(int i=1;i <= periodsNum;i++){
            RateTable rt = new RateTable();
            //应还利息
            BigDecimal ra = amnt.multiply(r).setScale(2,BigDecimal.ROUND_DOWN);
            //期数
            rt.setPeriods(i);
            //利息
            rt.setInterest(ra.doubleValue());
            //应还本金
            rt.setPrincipal(amt.doubleValue());
            amnt = amnt.subtract(amt);
            //剩余本金
            rt.setSubPrincipal(amnt.doubleValue());
            //最后一期把余数也还了
            if(i == periodsNum){
                rt.setPrincipal(amt.add(remainder).setScale(2,BigDecimal.ROUND_UP).doubleValue());
                rt.setSubPrincipal(0);
            }
            rateTables.add(rt);
        }
    }

    /**
     * 先息后本
     * @param amount
     * @param rate
     * @param periodsNum
     * @param rateTables
     */
    private static void c(double amount, double rate, int periodsNum,List<RateTable> rateTables){
        BigDecimal amnt = new BigDecimal(amount);
        //每期利率
        BigDecimal r = new BigDecimal(rate/12);
        for(int i=1;i <= periodsNum;i++){
            RateTable rt = new RateTable();
            //应还利息
            BigDecimal ra = amnt.multiply(r).setScale(2,BigDecimal.ROUND_DOWN);
            rt.setInterest(ra.doubleValue());
            rt.setPeriods(i);
            rt.setPrincipal(0);
            rt.setSubPrincipal(amount);
            if(i == periodsNum){
                rt.setSubPrincipal(0);
                rt.setPrincipal(amount);
            }
            rateTables.add(rt);
        }
    }

}

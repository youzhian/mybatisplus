package com.wofang.mybatisplus.model;

/**
 * 还款表
 */
public class RateTable {
    /**
     * 本期期数
     */
    int periods;
    /**
     * 本期偿还本金
     */
    double principal;
    /**
     * 本期利息
     */
    double interest;
    /**
     * 本期剩余本金
     */
    double subPrincipal;

    /**
     * 获取 本期期数
     *
     * @return periods 本期期数
     */
    public int getPeriods() {
        return this.periods;
    }

    /**
     * 设置 本期期数
     *
     * @param periods 本期期数
     */
    public void setPeriods(int periods) {
        this.periods = periods;
    }

    /**
     * 获取 本期偿还本金
     *
     * @return principal 本期偿还本金
     */
    public double getPrincipal() {
        return this.principal;
    }

    /**
     * 设置 本期偿还本金
     *
     * @param principal 本期偿还本金
     */
    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    /**
     * 获取 本期利息
     *
     * @return interest 本期利息
     */
    public double getInterest() {
        return this.interest;
    }

    /**
     * 设置 本期利息
     *
     * @param interest 本期利息
     */
    public void setInterest(double interest) {
        this.interest = interest;
    }

    /**
     * 获取 本期剩余本金
     *
     * @return subPrincipal 本期剩余本金
     */
    public double getSubPrincipal() {
        return this.subPrincipal;
    }

    /**
     * 设置 本期剩余本金
     *
     * @param subPrincipal 本期剩余本金
     */
    public void setSubPrincipal(double subPrincipal) {
        this.subPrincipal = subPrincipal;
    }
}

package com.oac.project.system.order.service;

public interface DataService {

    /**
     * 获取本年订单金额
     * @return
     */
    Long getYearSumMoney();

    /**
     * 获取去年订单金额
     * @return
     */
    Long getLastYearSumMoney();

    /**
     * 获取本月收账金额
     * @return
     */
    Long getMonthSumMoney();

    /**
     * 获取上月收账金额
     * @return
     */
    Long getLstMonthSumMoney();

    /**
     * 获取本年支出
     * @return
     */
    Long getYearPaySumMoney();

    /**
     * 获取去年支出
     * @return
     */
    Long getLastYearPaySumMoney();

    /**
     * 获取本月支出
     * @return
     */
    Long getMonthPaySumMoney();

    /**
     * 获取上月支出
     * @return
     */
    Long getLastMonthPaySumMoney();
}

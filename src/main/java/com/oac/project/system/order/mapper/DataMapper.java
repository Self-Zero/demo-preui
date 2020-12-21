package com.oac.project.system.order.mapper;

import com.oac.project.system.order.domain.MoneyUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataMapper {


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
     * 获取本年板材支出
     * @return
     */
    Long getYearPaySumMoneyBoard();

    /**
     * 获取去年板材支出
     * @return
     */
    Long getLastYearPaySumMoneyBoard();

    /**
     * 获取本年方料支出
     * @return
     */
    Long getYearPaySumMoneyBatten();

    /**
     * 获取去年方料支出
     * @return
     */
    Long getLastYearPaySumMoneyBatten();

    /**
     * 获取本月板材支出
     * @return
     */
    Long getMonthPaySumMoneyBoard();

    /**
     * 获取上月板材支出
     * @return
     */
    Long getLastMonthPaySumMoneyBoard();

    /**
     * 获取本月方料支出
     * @return
     */
    Long getMonthPaySumMoneyBatten();

    /**
     * 获取上月方料支出
     * @return
     */
    Long getLastMonthPaySumMoneyBatten();

    /**
     * 根据modeid查询该分类的订单的总金额
     * @param modeId
     * @return
     */
    Long getCountMoneyByMode(Long modeId);

    /**
     * 查询本年每月的收入情况
     * @return
     */
    List<MoneyUtil> getMoneyByMonth();

    /**
     * 查询本年每月的板材支出
     * @return
     */
    List<MoneyUtil> getBoardByMonth();

    /**
     * 查询本年每月的方料支出
     * @return
     */
    List<MoneyUtil> getBattenByMonth();
}

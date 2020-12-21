package com.oac.project.system.console.service;

import com.oac.project.system.console.vo.ShowEcharts;
import com.oac.project.system.console.vo.ShowMoneys;
import com.oac.project.system.order.domain.MoneyUtil;
import com.oac.project.system.order.domain.MoneyVO;

import java.util.List;

/**
 * ConsoleService接口
 */
public interface ConsoleService {

    /**
     * Echarts图表数据展示 CustomerName and Customer-Order-Count
     *
     * @return
     */
    List<ShowEcharts> getCustomerAndOrderCount();

    /**
     * Echarts 玫瑰图表展示数据
     * @return
     */
    List<ShowMoneys> getOrderIncomeByMode();

    /**
     * 查询本年每个月的收入情况
     * @return
     */
    List<MoneyVO> getMoneyByMonth();
}

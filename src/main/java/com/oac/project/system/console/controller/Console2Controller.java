package com.oac.project.system.console.controller;

import com.oac.project.common.utils.PageUtil;
import com.oac.project.system.console.service.ConsoleService;
import com.oac.project.system.console.vo.ShowMoneys;
import com.oac.project.system.order.domain.MoneyUtil;
import com.oac.project.system.order.domain.MoneyVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/system")
public class Console2Controller {

    @Resource
    private ConsoleService consoleService;

    /**
     * Echarts图表数据展示
     *
     * @return
     */
    @RequestMapping(value = "/echarts1/show2")
    public PageUtil<ShowMoneys> getOrderIncomeByMode() {
        //List<String> list = new ArrayList<>();
        //StringBuffer buffer = new StringBuffer();
        PageUtil<ShowMoneys> pageUtil = new PageUtil<>();
        try {
            List<ShowMoneys> lists = consoleService.getOrderIncomeByMode();
            pageUtil.setData(lists);
            pageUtil.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageUtil;
    }

    /**
     * 查询本年每个月的收入信息
     * @return
     */
    @RequestMapping(value = "/echarts1/show1")
    public PageUtil<MoneyVO> getMoneyByMonth() {
        PageUtil<MoneyVO> pageUtil = new PageUtil<>();
        try {
            List<MoneyVO> moneyByMonth = consoleService.getMoneyByMonth();
            /*for (MoneyVO moneyVO : moneyByMonth) {
                System.out.println(moneyVO);
            }*/
            pageUtil.setData(moneyByMonth);
            pageUtil.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageUtil;
    }

}

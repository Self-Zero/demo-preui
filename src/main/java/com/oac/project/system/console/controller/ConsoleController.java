package com.oac.project.system.console.controller;

import com.oac.project.common.utils.PageUtil;
import com.oac.project.system.console.service.ConsoleService;
import com.oac.project.system.console.vo.ShowEcharts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Console API
 */
@RestController
@RequestMapping(value = "/system")
public class ConsoleController {

    @Resource
    private ConsoleService consoleService;

    /**
     * Echarts图表数据展示
     *
     * @return
     */
    @RequestMapping(value = "/echarts/show")
    public PageUtil<ShowEcharts> getCustomerAndOrderCount() {
        PageUtil<ShowEcharts> jsonCustomer = new PageUtil<>();
        try {
            // 查询客户名称 订单条数的信息
            List<ShowEcharts> customerAndOrderCount = consoleService.getCustomerAndOrderCount();
            jsonCustomer.setData(customerAndOrderCount);
            jsonCustomer.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonCustomer;
    }
}

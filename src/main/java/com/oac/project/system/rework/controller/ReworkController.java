package com.oac.project.system.rework.controller;

import com.oac.project.common.utils.PageUtil;
import com.oac.project.system.order.vo.OrderVO;
import com.oac.project.system.rework.service.ReworkService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/system")
public class ReworkController {

    @Resource
    private ReworkService reworkService;

    /**
     * 分页查询全部的订单返工信息
     * @param page  当前页数
     * @param limit 显示条数
     * @param customerNameParam 客户姓名条件
     * @param orderNameParam    订单名称条件
     * @return  PageUtil<Rework>
     */
    @RequestMapping(value = "/rework/select")
    public PageUtil<OrderVO> getAllReworkOrderInfo(@RequestParam("page") Integer page,
                                                  @RequestParam("limit") Integer limit,
                                                  String customerNameParam,
                                                  String orderNameParam){
        String customerCondition = "";              // 客户名称条件
        if (customerNameParam == "" || customerNameParam == null) {
            customerCondition = null;
        } else {
            customerCondition = customerNameParam;
        }
        // 将orderNameParam处理 防止属性="" 查询的信息错误
        String orderCondition = "";                 // 订单名称条件
        if (orderNameParam == "" || orderNameParam == null) {
            orderCondition = null;
        } else {
            orderCondition = orderNameParam;
        }
        PageUtil<OrderVO> jsonRework = new PageUtil<>();
        Long count = 0L;
        List<OrderVO> allReworkOrderInfo = null;
        try {
            // 分页查询全部的订单信息（也可以根据条件查询相应的订单信息）
            allReworkOrderInfo = reworkService.getAllReworkOrderInfo(page, limit, customerCondition, orderCondition);
            // 根据条件查询订单的条数
            count = reworkService.getCount(customerCondition, orderCondition);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (allReworkOrderInfo != null) {
                jsonRework.setCode(0);
                jsonRework.setCount(count);
                jsonRework.setData(allReworkOrderInfo);
            } else {
                jsonRework.setCode(1);
                jsonRework.setCount(count);
                jsonRework.setData(null);
            }
        }
        // long endTime = System.currentTimeMillis();    //获取结束时间
        // System.out.println("controller程序运行时间：" + (endTime - startTime) + "ms");
        return jsonRework;
    }
}

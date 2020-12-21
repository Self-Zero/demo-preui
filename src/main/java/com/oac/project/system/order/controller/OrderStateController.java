package com.oac.project.system.order.controller;

import com.oac.project.common.utils.SaveUtil;
import com.oac.project.system.logistics.domain.Logistics;
import com.oac.project.system.logistics.service.LogisticsService;
import com.oac.project.system.order.service.OrderService;
import com.oac.project.system.order.vo.OrderVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单状态API 完工 发货 返工
 */
@RestController
@RequestMapping(value = "/state")
public class OrderStateController {

    @Resource
    private OrderService orderService;

    @Resource
    private LogisticsService logisticsService;

    /**
     * 订单完工
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/order/complete/{orderId}", method = RequestMethod.POST)
    public SaveUtil setOrderInfoByState(@PathVariable("orderId") Long orderId) {
        // System.out.println(orderId);
        SaveUtil orderUtil = new SaveUtil();
        Integer result = null;
        try {
            result = orderService.setOrderInfoByState(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                orderUtil.setSuccess(true);
                orderUtil.setMsg("订单完工");
            } else {
                orderUtil.setSuccess(false);
                orderUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return orderUtil;
    }

    /**
     * 订单发货
     *
     * @param logistics
     * @return
     */
    @RequestMapping(value = "/order/send", method = RequestMethod.POST)
    public SaveUtil sendOrderInfo(@RequestBody Logistics logistics) {
        // System.out.println(logistics);
        // System.out.println(logistics.getCreated() instanceof Date);
        // System.out.println(logistics.getOrderId() instanceof Long);
        SaveUtil orderUtil = new SaveUtil();
        Integer result = null;
        Integer results = null;
        // System.out.println(logistics.getCreated());
        try {
            result = orderService.setOrderInfoBySendDate(logistics.getCreated(), logistics.getOrderId());
            results = logisticsService.saveLogisticsInfo(logistics);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0 && results > 0) {
                orderUtil.setSuccess(true);
                orderUtil.setMsg("发货成功");
            } else {
                orderUtil.setSuccess(false);
                orderUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return orderUtil;
    }

    /**
     * 订单返工
     *
     * @param orderVO 需要返工的订单信息
     * @return SaveUtil
     */
    @RequestMapping(value = "/order/return", method = RequestMethod.POST)
    public SaveUtil setOrderInfoByReturn(@RequestBody OrderVO orderVO) {
        //System.out.println(orderVO);
        SaveUtil orderUtil = new SaveUtil();
        Integer result = null;
        try {
            result = orderService.setOrderInfoByReturn(orderVO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                orderUtil.setSuccess(true);
                orderUtil.setMsg("订单返工");
            } else {
                orderUtil.setSuccess(false);
                orderUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return orderUtil;
    }
}

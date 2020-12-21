package com.oac.project.system.order.controller;

import com.oac.project.common.utils.PageUtil;
import com.oac.project.common.utils.SaveUtil;
import com.oac.project.system.order.domain.OrderInfo;
import com.oac.project.system.order.mapper.OrderMapper;
import com.oac.project.system.order.service.OrderService;
import com.oac.project.system.order.vo.OrderVO;
import org.omg.CORBA.INTERNAL;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.IntToDoubleFunction;

/**
 * OrderInfo API
 */
@RestController
@RequestMapping(value = "/system")
public class OrderInfoController {

    @Resource
    private OrderService orderService;

    private List<OrderVO> allOrderInfo = null;

    /**
     * 分页查询全部的订单信息（也可以根据条件查询相应的订单信息）
     * 使用@RequestParam 解决参数名不一样的问题
     * <input type="text" name="orderNameParam" id="orderNameParam" class="layui-input">
     * name="orderNameParam"
     * 前台提交发送请求 这时后台想要接收参数 有两种方法
     * 1.方法名（String orderNameParam）
     * 2.方法名（@RequestParam（"orderNameParam"） String orderName）
     * 这两种方法都可以接收到数据
     * @param page 当前页数
     * @param limit 显示条数
     * @param customerNameParam 客户姓名条件
     * @param orderNameParam    订单名称条件
     * @return
     */
    @RequestMapping("/order/select")
    public PageUtil<OrderVO> allOrderInfo(@RequestParam("page") Integer page,
                                          @RequestParam("limit") Integer limit,
                                          String customerNameParam,
                                          String orderNameParam) {
        // long  startTime = System.currentTimeMillis();    //获取开始时间
        // System.out.println("page:" + page);
        // System.out.println("limit:" + limit);
        // System.out.println("customerNameParam:" + customerNameParam);
        // System.out.println("orderNameParam:" + orderNameParam);
        // System.out.print("customerNameParam与null比较:");
        // System.out.println(customerNameParam == null);
        // System.out.print("customerNameParam与\"\"比较");
        // System.out.println(customerNameParam == "");
        // System.out.print("orderNameParam与null比较");
        // System.out.println(orderNameParam == null);
        // System.out.print("orderNameParam与\"\"比较");
        // System.out.println(orderNameParam == "");
        // 将customerNameParam处理 防止属性="" 查询的信息错误
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
        PageUtil<OrderVO> jsonOrder = new PageUtil<>();
        Long count = 0L;
        try {
            // 分页查询全部的订单信息（也可以根据条件查询相应的订单信息）
            allOrderInfo = orderService.getAllOrderInfo(page, limit, customerCondition, orderCondition);
            // 根据条件查询订单的条数
            count = orderService.getCount(null, null, null, customerCondition, orderCondition);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (allOrderInfo != null) {
                jsonOrder.setCode(0);
                jsonOrder.setCount(count);
                jsonOrder.setData(allOrderInfo);
            } else {
                jsonOrder.setCode(1);
                jsonOrder.setCount(count);
                jsonOrder.setData(null);
            }
        }
        // long endTime = System.currentTimeMillis();    //获取结束时间
        // System.out.println("controller程序运行时间：" + (endTime - startTime) + "ms");
        return jsonOrder;
    }

    /**
     * 添加订单信息
     *
     * @param orderVO   需要添加的主题信息
     * @return          返回SaveUtil
     */
    @RequestMapping(value = "/order/save")
    public SaveUtil saveOrderInfo(@RequestBody OrderVO orderVO) {
        //System.out.println(orderVO);
        SaveUtil orderUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = orderService.saveOrderInfo(orderVO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                orderUtil.setSuccess(true);
                orderUtil.setMsg("添加成功");
            } else {
                orderUtil.setSuccess(false);
                orderUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return orderUtil;
    }

    /**
     * 修改订单信息
     *
     * @param orderVO   需要修改的主体信息
     * @return          返回SaveUtil
     */
    @RequestMapping(value = "/order/update")
    public SaveUtil updateOrderInfo(@RequestBody OrderVO orderVO) {
        // System.out.println(orderVO);
        SaveUtil orderUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = orderService.updateOrderInfo(orderVO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                orderUtil.setSuccess(true);
                orderUtil.setMsg("修改成功");
            } else {
                orderUtil.setSuccess(false);
                orderUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return orderUtil;
    }

    /**
     * 删除指定id的订单信息
     *
     * @param orderId   删除指定id的订单信息
     * @return          返回SaveUtil
     */
    @Transactional
    @RequestMapping(value = "/order/remove/{orderId}")
    public SaveUtil deleteOrderInfo(@PathVariable("orderId") Long orderId) {
        // System.out.println(orderId);
        SaveUtil orderUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = orderService.deleteOrderInfoById(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            if (result > 0) {
                orderUtil.setSuccess(true);
                orderUtil.setMsg("删除成功");
            } else {
                orderUtil.setSuccess(false);
                orderUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return orderUtil;
    }

    /**
     * 批量删除选中id的订单信息
     *
     * @param ids   批量选中的id
     * @return      返回SaveUtil
     */
    @RequestMapping(value = "/order/batchRemove/{ids}")
    public SaveUtil deleteMoreOrderInfo(@PathVariable("ids") Long[] ids) {
        /*for (Long id : ids) {
            System.out.println(id);
        }*/
        SaveUtil orderUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = orderService.deleteMoreOrderInfoById(ids);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                orderUtil.setSuccess(true);
                orderUtil.setMsg("批量删除成功");
            } else {
                orderUtil.setSuccess(false);
                orderUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return orderUtil;
    }
}

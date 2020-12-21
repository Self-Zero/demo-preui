package com.oac.project.system.distribution.controller;

import com.oac.project.common.utils.PageUtil;
import com.oac.project.common.utils.SaveUtil;
import com.oac.project.system.distribution.domain.OrderDistribution;
import com.oac.project.system.distribution.service.DistributionService;
import com.oac.project.system.distribution.vo.DistributionVO;
import com.oac.project.system.order.vo.OrderVO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/system")
public class DistributionController {

    @Resource
    private DistributionService distributionService;


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
     * @param orderNameParam    订单名称条件
     * @return
     */
    @RequestMapping("/distribution/select")
    public PageUtil<DistributionVO> allOrderInfo(@RequestParam("page") Integer page,
                                          @RequestParam("limit") Integer limit,
                                          String orderNameParam) {

        // 将orderNameParam处理 防止属性="" 查询的信息错误
        String orderCondition = "";                 // 订单名称条件
        if (orderNameParam == "" || orderNameParam == null) {
            orderCondition = null;
        } else {
            orderCondition = orderNameParam;
        }
        // System.out.println(orderCondition);
        PageUtil<DistributionVO> jsonOrder = new PageUtil<>();
        List<DistributionVO> orderDistributions = null;
        Long count = 0L;
        try {
            // 分页查询全部的订单信息（也可以根据条件查询相应的订单信息）
            orderDistributions = distributionService.getAllOrderDistribution(page, limit, orderCondition);
            // 根据条件查询订单的条数
            count = distributionService.getCount(orderCondition);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (orderDistributions != null) {
                jsonOrder.setCode(0);
                jsonOrder.setCount(count);
                jsonOrder.setData(orderDistributions);
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
     * 订单分配给员工任务
     *
     * @param orderDistribution   需要添加的主题信息
     * @return          返回SaveUtil
     */
    @RequestMapping(value = "/distribution/save")
    public SaveUtil saveOrderDistribution(@RequestBody OrderDistribution orderDistribution) {
        // System.out.println(orderDistribution);
        OrderDistribution distribution = distributionService.getOrderDistributionById(orderDistribution.getOrderId());
        SaveUtil orderUtil = new SaveUtil();
        if (distribution == null){
            Integer result = null;
            try {
                result = distributionService.saveOrderDistribution(orderDistribution);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (result != null) {
                    orderUtil.setSuccess(true);
                    orderUtil.setMsg("订单下发成功");
                } else {
                    orderUtil.setSuccess(false);
                    orderUtil.setMsg("系统异常,请及时联系管理员进行修复!");
                }
            }
        }else {
            orderUtil.setSuccess(false);
            orderUtil.setMsg("该订单已被分配给员工！");
        }
        return orderUtil;
    }

    /**
     * 订单分配给员工任务 -- 修改
     *
     * @param orderDistribution   需要修改的主体信息
     * @return          返回SaveUtil
     */
    @RequestMapping(value = "/distribution/update")
    public SaveUtil updateOrderDistribution(@RequestBody OrderDistribution orderDistribution) {
        // System.out.println(orderDistribution);
        SaveUtil orderUtil = new SaveUtil();
            Integer result = null;
            try {
                result = distributionService.updateOrderDistribution(orderDistribution);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (result != null) {
                    orderUtil.setSuccess(true);
                    orderUtil.setMsg("订单下发成功");
                } else {
                    orderUtil.setSuccess(false);
                    orderUtil.setMsg("系统异常,请及时联系管理员进行修复!");
                }
            }
        return orderUtil;
    }

    /**
     * 删除指定id的任务分配信息
     *
     * @param orderId   删除指定id的订单信息
     * @return          返回SaveUtil
     */
    @Transactional
    @RequestMapping(value = "/distribution/remove/{orderId}")
    public SaveUtil deleteOrderDistribution(@PathVariable("orderId") Long orderId) {
        System.out.println(orderId);
        SaveUtil orderUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = distributionService.deleteOrderDistribution(orderId);
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
}

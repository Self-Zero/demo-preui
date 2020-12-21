package com.oac.project.system.order.service;

import com.oac.project.system.order.domain.OrderInfo;
import com.oac.project.system.order.vo.OrderVO;

import java.util.Date;
import java.util.List;

/**
 * OrderService接口
 */
public interface OrderService {

    /**
     * 分页查询全部的订单信息（也可以根据条件查询相应的订单信息）
     *
     * @param page              当前页数
     * @param limit             显示条数
     * @param customerCondition 客户名称
     * @param orderCondition    订单名称
     * @return
     */
    List<OrderVO> getAllOrderInfo(Integer page, Integer limit, String customerCondition, String orderCondition);

    /**
     * 查询指定orderid的vo信息
     *
     * @param orderId
     * @return
     */
    OrderVO getOrderInfoByAllId(Long orderId);

    /**
     * 根据orderId查询指定订单信息
     *
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfoById(Long orderId);


    /**
     * 根据orderId查询指定订单信息
     *
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfoByIdDistribution(Long orderId);

    /**
     * 根据条件查询订单数量
     *
     * @param orderState        订单状态 （1-表示完工，0-表示未完工）
     * @param reworkDate        返工日期
     * @param customerId        客户Id
     * @param customerCondition 客户名称
     * @param orderCondition    订单名称
     * @return
     */
    Long getCount(Integer orderState, String reworkDate, Long customerId, String customerCondition, String orderCondition);

    /**
     * 添加订单信息
     *
     * @param orderVO
     * @return
     */
    Integer saveOrderInfo(OrderVO orderVO);

    /**
     * 修改订单信息
     *
     * @param orderVO
     * @return
     */
    Integer updateOrderInfo(OrderVO orderVO);

    /**
     * 删除订单信息
     *
     * @param orderId
     * @return
     */
    Integer deleteOrderInfoById(Long orderId);

    /**
     * 批量删除订单信息
     *
     * @param ids
     * @return
     */
    Integer deleteMoreOrderInfoById(Long[] ids);

    /**
     * 订单完工
     *
     * @return
     */
    Integer setOrderInfoByState(Long orderId);

    /**
     * 修改订单发货日期
     *
     * @param created
     * @return
     */
    Integer setOrderInfoBySendDate(Date created, Long orderId);

    /**
     * 订单返工
     *
     * @param orderVO 需要返工的订单信息
     * @return SaveUtil
     */
    Integer setOrderInfoByReturn(OrderVO orderVO);

}

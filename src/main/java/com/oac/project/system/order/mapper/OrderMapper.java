package com.oac.project.system.order.mapper;

import com.oac.project.system.order.domain.OrderInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * OrderMapper操作
 */
@Repository
public interface OrderMapper {

    /**
     * 查询Order订单的信息
     *
     * @return
     */
    List<OrderInfo> getAllOrderInfo(Integer page, Integer limit, String customerCondition, String orderCondition);

    /**
     * 查询order条数
     *
     * @return
     */
    Long getCount(Integer orderState, String reworkDate, Long customerId, String customerCondition, String orderCondition);

    /**
     * 添加订单信息
     *
     * @param order
     * @return
     */
    Integer saveOrderInfo(OrderInfo order);

    /**
     * 修改订单信息
     *
     * @param order
     * @return
     */
    Integer updateOrderInfo(OrderInfo order);

    /**
     * 删除指定orderId订单信息
     *
     * @param orderId
     * @return
     */
    Integer deleteOrderInfoById(Long orderId);

    /**
     * 批量删除指定id的订单信息
     *
     * @param ids
     * @return
     */
    /*Long deleteMoreOrderInfoById(Long[] ids);*/

    /**
     * 订单完工
     *
     * @param orderId
     * @return
     */
    Integer setOrderInfoByState(Long orderId);

    /**
     * 根据客户id查询改客户的订单数量
     *
     * @param customerId
     * @return
     */
    Long getOrderCountByCustomerId(Long customerId);

    /**
     * 修改订单发货日期
     *
     * @param created
     * @return
     */
    Integer setOrderInfoBySendDate(Date created, Long orderId);

    /**
     * 查询订单中发货日期不为空的数据
     *
     * @return
     */
    List<OrderInfo> getOrderInfoIsNotNull();

    /**
     * 订单返工
     *
     * @param orderId 选中的订单id
     * @return SaveUtil
     */
    Integer setOrderInfoByReturn(Long orderId);

    /**
     * 分页查询全部的返工的订单信息
     * @param page  当前页数
     * @param limit 显示条数
     * @param customerCondition 客户姓名条件
     * @param orderCondition    订单名称条件
     * @return  PageUtil<Rework>
     */
    List<OrderInfo> getAllReworkOrderInfo(Integer page, Integer limit, String customerCondition, String orderCondition);

    /**
     * 根据条件筛选查询返工数据的条数
     * @param customerCondition
     * @param orderCondition
     * @return Long
     */
    Long getReworkCount(String customerCondition, String orderCondition);

    /**
     * 根据orderId查询订单信息
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfoById(Long orderId);


    /**
     * 根据orderId查询订单信息
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfoByIdDistribution(Long orderId);
}
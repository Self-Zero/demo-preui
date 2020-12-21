package com.oac.project.system.rework.service;

import com.oac.project.system.order.vo.OrderVO;

import java.util.List;

/**
 * ReworkService 业务接口
 */
public interface ReworkService {

    /**
     * 分页查询全部的订单返工信息
     * @param page  当前页数
     * @param limit 显示条数
     * @param customerCondition 客户姓名条件
     * @param orderCondition    订单名称条件
     * @return  PageUtil<Rework>
     */
    List<OrderVO> getAllReworkOrderInfo(Integer page, Integer limit, String customerCondition, String orderCondition);

    /**
     * 根据条件筛选查询数据的条数
     * @param customerCondition
     * @param orderCondition
     * @return Long
     */
    Long getCount(String customerCondition, String orderCondition);
}

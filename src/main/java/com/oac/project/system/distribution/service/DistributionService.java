package com.oac.project.system.distribution.service;

import com.oac.project.system.distribution.domain.OrderDistribution;
import com.oac.project.system.distribution.vo.DistributionVO;

import java.util.List;

public interface DistributionService {

    /**
     * 订单分配给员工任务
     * @param orderDistribution
     * @return
     */
    Integer saveOrderDistribution(OrderDistribution orderDistribution);

    /**
     * 查询分配给员工的任务
     * @param page
     * @param limit
     * @param orderCondition
     * @return
     */
    List<DistributionVO> getAllOrderDistribution(Integer page, Integer limit, String orderCondition);

    /**
     * 查询被分配的任务条数
     * @param orderCondition
     * @return
     */
    Long getCount(String orderCondition);

    /**
     * 根据id查询该订单是否已经被分配
     * @param orderId
     * @return
     */
    OrderDistribution getOrderDistributionById(Long orderId);

    /**
     * 订单分配给员工任务 -- 修改
     * @param orderDistribution
     * @return
     */
    Integer updateOrderDistribution(OrderDistribution orderDistribution);

    /**
     * 删除指定id的任务分配信息
     * @param orderId
     * @return
     */
    Integer deleteOrderDistribution(Long orderId);
}

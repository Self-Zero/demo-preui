package com.oac.project.system.distribution.mapper;

import com.oac.project.system.distribution.domain.OrderDistribution;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributionMapper {

    /**
     * 查询分配给员工任务的信息
     * @param page
     * @param limit
     * @return
     */
    List<OrderDistribution> getAllOrderDistribution(Integer page, Integer limit, String orderCondition);

    /**
     * 订单分配给员工任务
     * @param orderDistribution
     * @return
     */
    Integer saveOrderDistribution(OrderDistribution orderDistribution);

    /**
     * 查询条数
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

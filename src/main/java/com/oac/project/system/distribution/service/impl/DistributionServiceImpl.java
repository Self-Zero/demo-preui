package com.oac.project.system.distribution.service.impl;

import com.alibaba.druid.sql.dialect.odps.ast.OdpsAddStatisticStatement;
import com.oac.project.system.city.domain.City;
import com.oac.project.system.city.mapper.CityMapper;
import com.oac.project.system.city.service.CityService;
import com.oac.project.system.customer.domain.Customer;
import com.oac.project.system.customer.service.CustomerService;
import com.oac.project.system.distribution.domain.OrderDistribution;
import com.oac.project.system.distribution.mapper.DistributionMapper;
import com.oac.project.system.distribution.service.DistributionService;
import com.oac.project.system.distribution.vo.DistributionVO;
import com.oac.project.system.order.domain.OrderInfo;
import com.oac.project.system.order.mapper.OrderMapper;
import com.oac.project.system.order.service.OrderService;
import com.oac.project.system.worker.domain.Worker;
import com.oac.project.system.worker.mapper.WorkerMapper;
import com.oac.project.system.worker.service.WorkerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class DistributionServiceImpl implements DistributionService {

    @Resource
    private DistributionMapper distributionMapper;

    /**
     * 订单分配给员工任务
     * @param orderDistribution
     * @return
     */
    @Override
    public Integer saveOrderDistribution(OrderDistribution orderDistribution) {
        Integer result = distributionMapper.saveOrderDistribution(orderDistribution);
        return result;
    }

    @Resource
    private OrderService orderService;
    @Resource
    private WorkerMapper workerMapper;
    @Resource
    private CustomerService customerService;
    @Resource
    private CityMapper cityMapper;

    /**
     * 查询分配给员工的任务
     * @param page
     * @param limit
     * @param orderCondition
     * @return
     */
    @Override
    public List<DistributionVO> getAllOrderDistribution(Integer page, Integer limit, String orderCondition) {
        if (page != null) {
            page = (page - 1) * limit;
        }
        List<DistributionVO> list = new ArrayList<>();
        DistributionVO vo = null;
        List<OrderDistribution> allOrderDistribution = distributionMapper.getAllOrderDistribution(page, limit, orderCondition);
        for (OrderDistribution distribution : allOrderDistribution) {
            vo = new DistributionVO();
            OrderInfo orderInfo = orderService.getOrderInfoByIdDistribution(distribution.getOrderId());
            Worker carpentryName = workerMapper.getWorkerById(distribution.getCarpentryId());
            Worker painterName = workerMapper.getWorkerById(distribution.getPainterId());
            Worker primerName = workerMapper.getWorkerById(distribution.getPrimerId());
            List<Customer> customerById = customerService.getCustomerById(orderInfo.getCustomerId());
            for (Customer customer : customerById) {
                vo.setCustomerName(customer.getCustomerName());
            }
            List<City> allCity = cityMapper.getAllCity();
            for (City city : allCity) {
                if (orderInfo.getAddressId().equals(city.getCityId())){
                    vo.setAddressName(city.getCityName());
                }
            }
            vo.setOrderId(distribution.getOrderId());
            vo.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(distribution.getCreated()));
            vo.setOrderName(orderInfo.getOrderName());
            vo.setCarpentryName(carpentryName.getWorkerName());
            vo.setPainterName(painterName.getWorkerName());
            vo.setPrimerName(primerName.getWorkerName());
            list.add(vo);
        }

        return list;
    }

    /**
     * 查询条数
     * @param orderCondition
     * @return
     */
    @Override
    public Long getCount(String orderCondition) {
        Long result = distributionMapper.getCount(orderCondition);
        return result;
    }

    /**
     * 根据id查询该订单是否已被分配
     * @param orderId
     * @return
     */
    @Override
    public OrderDistribution getOrderDistributionById(Long orderId) {
        OrderDistribution distribution = distributionMapper.getOrderDistributionById(orderId);
        return distribution;
    }

    /**
     * 订单分配给员工任务 -- 修改
     * @param orderDistribution
     * @return
     */
    @Override
    public Integer updateOrderDistribution(OrderDistribution orderDistribution) {
        Integer result = distributionMapper.updateOrderDistribution(orderDistribution);
        return result;
    }

    /**
     * 删除指定id的任务分配信息
     * @param orderId
     * @return
     */
    @Override
    public Integer deleteOrderDistribution(Long orderId) {
        Integer result = distributionMapper.deleteOrderDistribution(orderId);
        return result;
    }
}

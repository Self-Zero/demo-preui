package com.oac.project.system.customer.service;


import com.oac.project.system.customer.domain.Customer;
import com.oac.project.system.customer.vo.CustomerVO;

import java.util.List;

/**
 * Customer Service 接口
 */
public interface CustomerService {

    /**
     * 根据Urban(二级)城市id查询用户信息
     *
     * @param cityId Urban(二级)城市id
     * @return
     */
    List<Customer> getCustomerByCityId(Long cityId);

    /**
     * 根据用户id查询用户信息
     *
     * @param customerId 用户id
     * @return
     */
    List<Customer> getCustomerById(Long customerId);

    /**
     * 查询全部的客户信息
     *
     * @param page
     * @param limit
     * @param customerName
     * @param customerPhone
     * @return
     */
    List<CustomerVO> getAllCustomer(Integer page, Integer limit, String customerName, String customerPhone);

    /**
     * 查询全部客户的数量
     *
     * @param customerName
     * @param customerPhone
     * @return
     */
    Long getCount(String customerName, String customerPhone);

    /**
     * 添加客户信息
     *
     * @param customer
     * @return
     */
    Integer saveCustomerInfo(Customer customer);

    /**
     * 删除指定id的客户信息
     *
     * @param customerId
     * @return
     */
    Integer deleteCustomerInfo(Long customerId);

    /**
     * 批量删除选中id的客户信息
     *
     * @param ids
     * @return
     */
    Integer deleteMoreCustomerInfo(Long[] ids);

    /**
     * 修改客户信息
     *
     * @param customer
     * @return
     */
    Integer updateCustomerInfo(Customer customer) throws Exception;
}

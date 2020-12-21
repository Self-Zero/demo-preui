package com.oac.project.system.customer.mapper;

import com.oac.project.system.customer.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMapper {

    /**
     * 获取所有的Customer客户信息
     *
     * @return
     */
    List<Customer> getAllCustomer(Integer page, Integer limit, String customerName, String customerPhone);

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
     * 修改客户信息
     *
     * @param customer
     * @return
     */
    Integer updateCustomerInfo(Customer customer);
}

package com.oac.project.system.customer.service.impl;

import com.oac.project.system.city.domain.City;
import com.oac.project.system.city.mapper.CityMapper;
import com.oac.project.system.customer.domain.Customer;
import com.oac.project.system.customer.mapper.CustomerMapper;
import com.oac.project.system.customer.service.CustomerService;
import com.oac.project.system.customer.vo.CustomerVO;
import com.oac.project.system.order.domain.OrderInfo;
import com.oac.project.system.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer操作
 */
@Service
public class CustomerServiceImpl implements CustomerService {


    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private CityMapper cityMapper;
    @Resource
    private OrderMapper orderMapper;

    private List<Customer> allCustomer = null;

    /**
     * 根据Urban(二级)城市id查询用户信息
     *
     * @param cityId Urban(二级)城市id
     * @return
     */
    @Override
    public List<Customer> getCustomerByCityId(Long cityId) {
        List<Customer> customerByCity = new ArrayList<>();
        Customer customer = null;
        allCustomer = customerMapper.getAllCustomer(null, null, null, null);
        for (Customer cus : allCustomer) {
            customer = new Customer();
            if (cus.getAddressId().equals(cityId)) {
                customer.setCustomerId(cus.getCustomerId());
                customer.setCustomerName(cus.getCustomerName());
                customerByCity.add(customer);
            }
        }
        return customerByCity;
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param customerId 用户id
     * @return
     */
    @Override
    public List<Customer> getCustomerById(Long customerId) {
        List<Customer> customerById = new ArrayList<>();
        Customer customer = null;
        allCustomer = customerMapper.getAllCustomer(null, null, null, null);
        for (Customer cus : allCustomer) {
            customer = new Customer();
            if (cus.getCustomerId().equals(customerId)) {
                customer.setCustomerId(cus.getCustomerId());
                customer.setCustomerName(cus.getCustomerName());
                customer.setAddressId(cus.getAddressId());
                customer.setPhone(cus.getPhone());
                customer.setCreated(cus.getCreated());
                customerById.add(customer);
            }
        }
        return customerById;
    }

    /**
     * 查询全部的客户
     *
     * @param page
     * @param limit
     * @param customerName
     * @param customerPhone
     * @return
     */
    @Override
    public List<CustomerVO> getAllCustomer(Integer page, Integer limit, String customerName, String customerPhone) {
        if (page != null) {
            page = (page - 1) * limit;
        }
        // 声明VO集合
        List<CustomerVO> customerVOS = new ArrayList<>();
        // 查询全部的Customer信息
        allCustomer = customerMapper.getAllCustomer(page, limit, customerName, customerPhone);
        // 查询全部的City信息
        List<City> allCity = cityMapper.getAllCity();
        /**
         * 规范:
         *      在循环外创建对象,只创建一个对象,循环中new的是对象的引用
         *      在循环内创建对象，每次循环都会创建一个新的对象，从而增加系统的负担
         */
        CustomerVO vo = null;               // 声明VO对象
        StringBuffer a = null;              // 声明StringBuffer对象
        for (Customer customer : allCustomer) {
            a = new StringBuffer();// 创建Buffer对象的引用
            vo = new CustomerVO();// 创建VO对象的引用
            vo.setOrderNumber(orderMapper.getOrderCountByCustomerId(customer.getCustomerId()));
            vo.setCustomerId(customer.getCustomerId());
            vo.setCustomerName(customer.getCustomerName());
            vo.setAddressId(customer.getAddressId());
            vo.setCustomerPhone(customer.getPhone());
            vo.setCreated(new SimpleDateFormat("yyyy-DD-mm").format(customer.getCreated()));
            for (City city : allCity) {
                if (customer.getAddressId().equals(city.getCityId())) {
                    for (City citys : allCity) {
                        if (city.getParentId().equals(citys.getCityId())) {
                            vo.setParentId(citys.getCityId());
                            // 将一级城市的名字追加至 StringBuffer 中
                            a.append(citys.getCityName());
                            vo.setCityName(city.getCityName());
                            // 将二级城市的名字追加至 StringBuffer 中
                            a.append("-" + city.getCityName());
                            // 这时将拼接的StringBuffer添加到VO中
                            vo.setCustomerAddress(a);
                        }
                    }
                }
            }
            customerVOS.add(vo);
        }
        return customerVOS;
    }

    /**
     * 查询全部客户的数量
     *
     * @param customerName
     * @param customerPhone
     * @return
     */
    @Override
    public Long getCount(String customerName, String customerPhone) {
        Long count = customerMapper.getCount(customerName, customerPhone);
        return count;
    }

    /**
     * 添加客户信息
     *
     * @param customer
     * @return
     */
    @Override
    public Integer saveCustomerInfo(Customer customer) {
        Integer result = customerMapper.saveCustomerInfo(customer);
        return result;
    }

    /**
     * 删除指定id的客户信息
     *
     * @param customerId
     * @return
     */
    @Override
    public Integer deleteCustomerInfo(Long customerId) {
        List<OrderInfo> allOrderInfo = orderMapper.getAllOrderInfo(null, null, null, null);
        for (OrderInfo orderInfo : allOrderInfo) {
            if (customerId.equals(orderInfo.getCustomerId())) {
                return null;
            } else {
                Integer result = customerMapper.deleteCustomerInfo(customerId);
                return result;
            }
        }
        return null;
    }

    /**
     * 批量删除选中id的客户信息
     *
     * @param ids
     * @return
     */
    @Override
    public Integer deleteMoreCustomerInfo(Long[] ids) {
        List<OrderInfo> allOrderInfo = orderMapper.getAllOrderInfo(null, null, null, null);
        for (int i = 0; i < ids.length; i++) {
            for (OrderInfo orderInfo : allOrderInfo) {
                if (ids[i].equals(orderInfo.getCustomerId())) {
                    return null;
                } else {
                    Integer result = customerMapper.deleteCustomerInfo(ids[i]);
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 修改客户信息
     *
     * @param customer
     * @return
     */
    @Transactional
    @Override
    public Integer updateCustomerInfo(Customer customer) throws Exception {
        Integer result = null;
        try {
            result = customerMapper.updateCustomerInfo(customer);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }
}

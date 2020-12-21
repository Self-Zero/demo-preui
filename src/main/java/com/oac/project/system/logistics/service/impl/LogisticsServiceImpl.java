package com.oac.project.system.logistics.service.impl;

import com.oac.project.system.city.domain.City;
import com.oac.project.system.city.mapper.CityMapper;
import com.oac.project.system.customer.domain.Customer;
import com.oac.project.system.customer.mapper.CustomerMapper;
import com.oac.project.system.logistics.domain.Logistics;
import com.oac.project.system.logistics.domain.LogisticsCompany;
import com.oac.project.system.logistics.mapper.LogisticsCompanyMapper;
import com.oac.project.system.logistics.mapper.LogisticsMapper;
import com.oac.project.system.logistics.service.LogisticsService;
import com.oac.project.system.logistics.vo.LogisticsVO;
import com.oac.project.system.order.domain.OrderInfo;
import com.oac.project.system.order.mapper.OrderMapper;
import com.oac.project.system.order.service.OrderService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LogisticsServiceImpl implements LogisticsService {


    @Resource
    private OrderMapper orderMapper;
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private LogisticsCompanyMapper logisticsCompanyMapper;
    @Resource
    private LogisticsMapper logisticsMapper;
    @Resource
    private CityMapper cityMapper;
    @Resource
    private OrderService orderService;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 查询全部的物流发货信息
     *
     * @param page      当前页数
     * @param limit     显示条数
     * @param orderName 订单名称
     * @param sendDate  发货日期
     * @return
     */
    @Override
    public List<LogisticsVO> getLogisticsInfo(Integer page, Integer limit, String orderName, String sendDate) {
        // 设置--page
        if (page != null) {
            page = (page - 1) * limit;
        }
        // 查询订单中发货日期不为空的数据
        List<OrderInfo> allOrderInfo = orderMapper.getOrderInfoIsNotNull();
        // 查询全部的客户信息
        List<Customer> allCustomer = customerMapper.getAllCustomer(null,null,null,null);
        // 查询全部的城市信息
        List<City> allCity = cityMapper.getAllCity();
        // 查询全部的物流公司信息
        List<LogisticsCompany> allLogisticsCompany = logisticsCompanyMapper.getAllLogisticsCompany();
        // 查询全部的物流发货信息（包含分页,可以查询全部的物流发货信息,也可以根据条件筛选查询物流发货信息--订单名称；发货日期）
        List<Logistics> allLogistics = logisticsMapper.getAllLogistics(page, limit, orderName, sendDate);
        // 创建vo集合
        List<LogisticsVO> logisticsVOS = new ArrayList<>();
        // 创建vo对象
        LogisticsVO vo = null;
        // 创建拼接省事-城市的String对象
        StringBuffer result = null;
        // 遍历全部的物流发货信息
        for (Logistics logistics : allLogistics) {
            // 创建StringBuffer对象的引用
            result = new StringBuffer();
            // 创建vo对象的引用
            vo = new LogisticsVO();
            // 将logisticsId添加至vo中
            vo.setLogisticsId(logistics.getLogisticsId());
            // 将orderId添加至vo中
            vo.setOrderId(logistics.getOrderId());
            // 将customerId添加至vo中
            vo.setCustomerId(logistics.getCustomerId());
            // 将logisticsCompanyId添加至vo中
            vo.setLogisticsCompanyId(logistics.getLogisticsCompanyId());
            // 将物流件数添加至vo中
            vo.setLogisticsNumber(logistics.getLogisticsNumber());
            // 将物流价格添加至vo中
            vo.setLogisticsPrice(logistics.getLogisticsPrice());
            // 将创建日期添加至vo中 并转换日期的格式 yyyy-MM-dd
            vo.setCreated(simpleDateFormat.format(logistics.getCreated()));
            // 编辑order对象
            for (OrderInfo orderInfo : allOrderInfo) {
                // 判断查询出来的物流发货信息中的orderId和order中的Id是否相等
                if (logistics.getOrderId().equals(orderInfo.getOrderId())) {
                    // 将orderName添加至vo中
                    vo.setOrderName(orderInfo.getOrderName());
                }
            }
            // 遍历客户信息
            for (Customer customer : allCustomer) {
                // 判断查询出来的物流发货信息中的customerId和customer中的Id是否相等
                if (logistics.getCustomerId().equals(customer.getCustomerId())) {
                    // 遍历城市信息
                    for (City city : allCity) {
                        // 判断客户中的addressId和city中的cityId是否相等
                        if (customer.getAddressId().equals(city.getCityId())) {
                            // 遍历省份信息
                            for (City citys : allCity) {
                                // 判断city中的父id和citys中的cityId是否相等
                                if (city.getParentId().equals(citys.getCityId())) {
                                    // 将省份信息追加至StringBuffer中
                                    result.append(citys.getCityName());
                                    // 将城市信息追加至StringBuffer中
                                    result.append("-" + city.getCityName());
                                }
                            }
                        }
                    }
                    // 将客户地址添加至vo中
                    vo.setCustomerAddress(result);
                    // 将客户名称添加至vo中
                    vo.setCustomerName(customer.getCustomerName());
                    // 将客户的联系方式添加至vo中
                    vo.setCustomerPhone(customer.getPhone());
                }
            }
            // 遍历物流公司信息
            for (LogisticsCompany logisticsCompany : allLogisticsCompany) {
                // 判断查询出来的物流发货信息中的logisticsCompanyId和logisticsCompany中的companyId是否相等
                if (logistics.getLogisticsCompanyId().equals(logisticsCompany.getCompanyId())) {
                    // 将companyName添加至vo中
                    vo.setCompanyName(logisticsCompany.getCompanyName());
                    // 将companyPhone添加至vo中
                    vo.setCompanyPhone(logisticsCompany.getCompanyPhone());
                }
            }
            // 将vo对象的应用添加至vo集合中
            logisticsVOS.add(vo);
        }
        return logisticsVOS;
    }

    /**
     * 根据Id查询相应的物流发货信息
     *
     * @param logisticsId 指定的物流发货信息ID
     * @return
     */
    @Override
    public LogisticsVO getLogisticsById(Long logisticsId) {
        // 查询查询订单中发货日期不为空的数据
        List<OrderInfo> allOrderInfo = orderMapper.getOrderInfoIsNotNull();
        // 插叙全部的客户信息
        List<Customer> allCustomer = customerMapper.getAllCustomer(null,null,null,null);
        // 查询全部的城市信息
        List<City> allCity = cityMapper.getAllCity();
        // 查询全部的物流公司信息
        List<LogisticsCompany> allLogisticsCompany = logisticsCompanyMapper.getAllLogisticsCompany();
        // 根据id查询对应的物流发货信息
        Logistics logisticsById = logisticsMapper.getLogisticsById(logisticsId);
        // 创建vo对象
        LogisticsVO vo = new LogisticsVO();
        // 创建拼接省事-城市的String对象
        StringBuffer result = new StringBuffer();
        // 将logisticsId添加至vo中
        vo.setLogisticsId(logisticsById.getLogisticsId());
        // 将orderId添加至vo中
        vo.setOrderId(logisticsById.getOrderId());
        // 将customerId添加至vo中
        vo.setCustomerId(logisticsById.getCustomerId());
        // 将logisticsCompanyId添加至vo中
        vo.setLogisticsCompanyId(logisticsById.getLogisticsCompanyId());
        // 将物流件数添加至vo中
        vo.setLogisticsNumber(logisticsById.getLogisticsNumber());
        // 将物流价格添加至vo中
        vo.setLogisticsPrice(logisticsById.getLogisticsPrice());
        // 将创建日期添加至vo中 并转换日期的格式 yyyy-MM-dd
        vo.setCreated(simpleDateFormat.format(logisticsById.getCreated()));
        // 编辑order对象
        for (OrderInfo orderInfo : allOrderInfo) {
            // 判断查询出来的物流发货信息中的orderId和order中的Id是否相等
            if (logisticsById.getOrderId().equals(orderInfo.getOrderId())) {
                // 将orderName添加至vo中
                vo.setOrderName(orderInfo.getOrderName());
            }
        }
        // 遍历客户信息
        for (Customer customer : allCustomer) {
            // 判断查询出来的物流发货信息中的customerId和customer中的Id是否相等
            if (logisticsById.getCustomerId().equals(customer.getCustomerId())) {
                // 遍历城市信息
                for (City city : allCity) {
                    // 判断客户中的addressId和city中的cityId是否相等
                    if (customer.getAddressId().equals(city.getCityId())) {
                        // 遍历省份信息
                        for (City citys : allCity) {
                            // 判断city中的父id和citys中的cityId是否相等
                            if (city.getParentId().equals(citys.getCityId())) {
                                // 将省份信息追加至StringBuffer中
                                result.append(citys.getCityName());
                                // 将城市信息追加至StringBuffer中
                                result.append("-" + city.getCityName());
                            }
                        }
                    }
                }
                // 将客户地址添加至vo中
                vo.setCustomerAddress(result);
                // 将客户名称添加至vo中
                vo.setCustomerName(customer.getCustomerName());
                // 将客户的联系方式添加至vo中
                vo.setCustomerPhone(customer.getPhone());
            }
        }
        // 遍历物流公司信息
        for (LogisticsCompany logisticsCompany : allLogisticsCompany) {
            // 判断查询出来的物流发货信息中的logisticsCompanyId和logisticsCompany中的companyId是否相等
            if (logisticsById.getLogisticsCompanyId().equals(logisticsCompany.getCompanyId())) {
                // 将companyName添加至vo中
                vo.setCompanyName(logisticsCompany.getCompanyName());
                // 将companyPhone添加至vo中
                vo.setCompanyPhone(logisticsCompany.getCompanyPhone());
            }
        }
        return vo;
    }

    /**
     * 查询全部物流发货信息的条数
     *
     * @return
     */
    @Override
    public Long getCount() {
        Long count = logisticsMapper.getCount();
        return count;
    }

    /**
     * 添加物流发货信息
     *
     * @param logistics 输入的物流发货信息
     * @return
     */
    @Override
    @Transactional
    public Integer saveLogisticsInfo(Logistics logistics) {
        Integer result = null;
        // 查询全部的订单信息
        OrderInfo orderInfoByIds = orderService.getOrderInfoById(logistics.getOrderId());
        // 判断输入的物流发货信息中的orderId和全部订单信息中的orderId是否相等
        if (logistics.getOrderId().equals(orderInfoByIds.getOrderId())) {
            // 将customerId添加到logistics中
            logistics.setCustomerId(orderInfoByIds.getCustomerId());
        }
        try {
            // 添加物流发货信息
            result = logisticsMapper.saveLogisticsInfo(logistics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除指定id的物流发货信息
     *
     * @param logisticsId 指定的物流发货信息ID
     * @return
     */
    @Override
    @Transactional
    public Integer deleteLogisticsInfoById(Long logisticsId) {
        Integer resultOrder = null;
        Integer result = null;
        Date created = null;
        // 查询全部的物流发货信息
        List<Logistics> allLogistics = logisticsMapper.getAllLogistics(null, null, null, null);
        // 遍历物流发货信息
        for (Logistics logistics : allLogistics) {
            // logisticsId和logistics中的id是否相等
            if (logisticsId.equals(logistics.getLogisticsId())) {
                try {
                    // 修改order中的 sendDate=null
                    resultOrder = orderMapper.setOrderInfoBySendDate(created, logistics.getOrderId());
                    // 根据id删除指定的物流发货信息
                    result = logisticsMapper.deleteLogisticsInfoById(logisticsId);
                } catch (SqlSessionException e) {
                    e.printStackTrace();
                }
            }
        }
        if (resultOrder > 0 && result > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 批量删除选中id的物流发货信息
     *
     * @param ids 批量删除选中的logisticsId
     * @return
     */
    @Override
    @Transactional
    public Integer deleteMoreLogisticsInfo(Long[] ids) {
        Integer resultOrder = null;
        Integer result = null;
        Date created = null;
        // 查询全部的物流发货信息
        List<Logistics> allLogistics = logisticsMapper.getAllLogistics(null, null, null, null);
        // 遍历物流发货信息
        for (Logistics logistics : allLogistics) {
            // 循环ids数组
            for (int i = 0; i < ids.length; i++) {
                // 判断数组ids的id和logistics中的id是否相等
                if (ids[i].equals(logistics.getLogisticsId())) {
                    try {
                        // 修改order中的 sendDate=null
                        resultOrder = orderMapper.setOrderInfoBySendDate(created, logistics.getOrderId());
                        // 根据id删除指定的物流发货信息
                        result = logisticsMapper.deleteLogisticsInfoById(ids[i]);
                    } catch (SqlSessionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (resultOrder > 0 && result > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 编辑物流发货信息
     *
     * @param LogisticsVO 需要编辑的物流发货信息
     * @return
     */
    @Override
    @Transactional
    public Integer updateLogisticsInfo(LogisticsVO LogisticsVO) {
        Logistics logistics = new Logistics();
        logistics.setLogisticsId(LogisticsVO.getLogisticsId());
        logistics.setLogisticsCompanyId(LogisticsVO.getLogisticsCompanyId());
        logistics.setLogisticsNumber(LogisticsVO.getLogisticsNumber());
        logistics.setLogisticsPrice(LogisticsVO.getLogisticsPrice());
        Integer result = null;
        try {
            // 编辑物流发货信息
            result = logisticsMapper.updateLogisticsInfo(logistics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

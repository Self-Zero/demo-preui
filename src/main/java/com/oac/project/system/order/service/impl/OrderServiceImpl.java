package com.oac.project.system.order.service.impl;

import com.oac.project.system.batten.domain.BattenType;
import com.oac.project.system.batten.domain.BattenVersion;
import com.oac.project.system.batten.mapper.BattenTypeMapper;
import com.oac.project.system.city.domain.City;
import com.oac.project.system.city.mapper.CityMapper;
import com.oac.project.system.customer.domain.Customer;
import com.oac.project.system.customer.mapper.CustomerMapper;
import com.oac.project.system.logistics.domain.Logistics;
import com.oac.project.system.logistics.mapper.LogisticsMapper;
import com.oac.project.system.material.domain.Material;
import com.oac.project.system.material.mapper.MaterialMapper;
import com.oac.project.system.mode.domain.Mode;
import com.oac.project.system.mode.mapper.ModeMapper;
import com.oac.project.system.order.domain.OrderInfo;
import com.oac.project.system.order.mapper.DataMapper;
import com.oac.project.system.order.mapper.OrderMapper;
import com.oac.project.system.order.service.OrderService;
import com.oac.project.system.order.vo.OrderVO;
import com.oac.project.system.rework.domain.Rework;
import com.oac.project.system.rework.mapper.ReworkMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * OrderService操作
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private BattenTypeMapper battenMapper;
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private CityMapper cityMapper;
    @Resource
    private MaterialMapper materialMapper;
    @Resource
    private ModeMapper modeMapper;
    @Resource
    private LogisticsMapper logisticsMapper;

    private List<OrderInfo> allOrder = null;
    private List<BattenType> allBattenType = null;
    private List<BattenVersion> allBattenVersion = null;
    private List<Customer> allCustomer = null;
    private List<City> allCity = null;
    private List<Material> allMaterial = null;
    private List<Mode> allMode = null;
    private List<OrderVO> orderVO = null;

    /**
     * 分页查询全部的订单信息（也可以根据条件查询相应的订单信息）
     *
     * @param page              当前页数
     * @param limit             显示条数
     * @param customerCondition 客户名称
     * @param orderCondition    订单名称
     * @return
     */
    @Override
    public List<OrderVO> getAllOrderInfo(Integer page, Integer limit, String customerCondition, String orderCondition) {

        // long  startTime = System.currentTimeMillis();    //获取开始时间

        // 声明VO的集合
        orderVO = new ArrayList<>();
        // page处理
        if (page != null) {
            page = (page - 1) * limit;
        }
        // 声明vo对象
        OrderVO vo = null;
        // 声明日期转换格式的对象及引用
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月dd日-----HH:mm:ss");
        allOrder = orderMapper.getAllOrderInfo(page, limit, customerCondition, orderCondition);                    // 订单
        allBattenType = battenMapper.getAllBattenType();                                                           // 方料
        allBattenVersion = battenMapper.getAllBattenVersion();                                                     // 型号
        allCustomer = customerMapper.getAllCustomer(null,null,null,null);  // 客户
        allCity = cityMapper.getAllCity();                                                                         // 城市
        allMaterial = materialMapper.getAllMaterial();                                                             // 材质
        allMode = modeMapper.getAllMode();                                                                         // 类型（整梯，铺板，护栏，铺板+护栏）
        /*将需要展现在页面的数据封装在VO中，分解多表关联查询，降低数据库的压力*/
        /*System.out.println("------------------------------------------------------------------------------>");*/
        String sendDate = null;
        String reworkDate = null;
        String created = null;
        // 遍历订单信息
        for (OrderInfo order : allOrder) {
            // 创建vo对象的引用
            vo = new OrderVO();
            vo.setOrderId(order.getOrderId());
            vo.setOrderName(order.getOrderName());
            vo.setOrderNumber(order.getOrderNumber());
            vo.setOrderColor(order.getOrderColor());
            vo.setOrderPrice(order.getOrderPrice());
            vo.setOrderTotal(order.getOrderTotal());
            vo.setOrderState(order.getOrderState());
            vo.setRemarks(order.getRemarks());
            vo.setDemandPic1(order.getDemandPic1());
            vo.setDemandPic2(order.getDemandPic2());
            vo.setDemandPic3(order.getDemandPic3());
            vo.setDemandPic4(order.getDemandPic4());
            vo.setDemandPic5(order.getDemandPic5());
            vo.setDesignChart(order.getDesignChart());
            // 转换日期格式
            if (order.getSendDate() != null) {
                sendDate = sdf.format(order.getSendDate());
                vo.setSendDate(sendDate);
            }
            if (order.getReworkDate() != null) {
                reworkDate = sdf.format(order.getReworkDate());
                vo.setReworkDate(reworkDate);
            }
            if (order.getCreated() != null) {
                created = sdf.format(order.getCreated());
                vo.setCreated(created);
            }
            // 遍历客户信息
            for (Customer customer : allCustomer) {
                // orderInfo中的用户id 在 customer中是否拥有
                if (order.getCustomerId().equals(customer.getCustomerId())) {
                    // 提取用户的信息到OrderVO中
                    vo.setCustomerId(order.getCustomerId());
                    vo.setCustomerName(customer.getCustomerName());
                    vo.setPhone(customer.getPhone());
                }
                for (City city : allCity) {
                    // order的customer的cityId是否相等，且Customer的cityId和City的cityId是否相等
                    if (order.getAddressId().equals(customer.getAddressId()) && customer.getAddressId().equals(city.getCityId())) {
                        // 将对应的city中的信息提取到OrderVO中
                        vo.setAddressId(city.getCityId());
                        vo.setCityName(city.getCityName());
                        vo.setParentId(city.getParentId());
                    }
                }
            }

            for (BattenVersion version : allBattenVersion) {
                // 提取柱型信息
                if (order.getBattenVersionId() != null) {
                    if (order.getBattenVersionId().equals(version.getBattenVersionId())) {
                        vo.setBattenVersionId(version.getBattenVersionId());
                        vo.setBattenVersionName(version.getBattenVersionName());
                    }
                }
            }

            for (Material material : allMaterial) {
                // 提取材质信息
                if (material.getMaterialId().equals(order.getMaterialId())) {
                    vo.setMaterialId(material.getMaterialId());
                    vo.setMaterialName(material.getMaterialName());
                }
            }
            for (BattenType battenType : allBattenType) {
                // 提取大方料规格信息
                if (battenType.getBattenTypeId().equals(order.getBattenBigId())) {
                    vo.setBattenBigId(battenType.getBattenTypeId());
                    vo.setBattenBigName(battenType.getBattenName());
                }
                // 提取小方料规格信息
                if (battenType.getBattenTypeId().equals(order.getBattenSmallId())) {
                    vo.setBattenSmallId(battenType.getBattenTypeId());
                    vo.setBattenSmallName(battenType.getBattenName());
                }
            }
            for (Mode mode : allMode) {
                // 提取产品类型
                if (mode.getModeId().equals(order.getModeId())) {
                    vo.setModeId(mode.getModeId());
                    vo.setModeName(mode.getModeName());
                }
            }
            orderVO.add(vo);
        }
        // long endTime = System.currentTimeMillis();    //获取结束时间
        // System.out.println("order程序运行时间：" + (endTime - startTime) + "ms");
        /*System.out.println("------------------------------------------------------------------------------>");
        for (OrderVO o : orderVO) {
            System.out.println("全部信息：" + o);
        }
        System.out.println("------------------------------------------------------------------------------>");*/
        return orderVO;

    }

    /**
     * 根据orderId查询指定的订单信息
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderInfo getOrderInfoById(Long orderId) {
        // 先查询全部的订单信息
        // List<OrderInfo> allOrderInfo = orderMapper.getAllOrderInfo(null, null, null, null);
        // 遍历订单信息
        // System.out.println(orderId);
        if (allOrder != null){
            for (OrderInfo orderInfo : allOrder) {
                // 如果选中的orderId == 对象的id
                if (orderId.equals(orderInfo.getOrderId())) {
                    // 直接返回这个对象即可
                    return orderInfo;
                }
            }
        }else {
            OrderInfo orderInfo = orderMapper.getOrderInfoById(orderId);
            return orderInfo;
        }
        return null;
    }

    /**
     * 根据orderId查询指定的订单信息
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderInfo getOrderInfoByIdDistribution(Long orderId) {
        // 先查询全部的订单信息
        // List<OrderInfo> allOrderInfo = orderMapper.getAllOrderInfo(null, null, null, null);
        // 遍历订单信息
        // System.out.println(orderId);
            OrderInfo orderInfo = orderMapper.getOrderInfoByIdDistribution(orderId);
            // System.out.println(orderInfo);
            return orderInfo;
    }

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
    @Override
    public Long getCount(Integer orderState, String reworkDate, Long customerId, String customerCondition, String orderCondition) {
        // long startTime = System.currentTimeMillis();    //获取开始时间
        Long count = orderMapper.getCount(orderState, reworkDate, customerId, customerCondition, orderCondition);
        // long endTime = System.currentTimeMillis();    //获取结束时间
        // System.out.println("count程序运行时间：" + (endTime - startTime) + "ms");
        return count;
    }

    /**
     * 添加订单信息
     *
     * @param orderVO
     * @return
     */
    @Override
    public Integer saveOrderInfo(OrderVO orderVO) {
        OrderInfo order = new OrderInfo();
        order.setAddressId(orderVO.getAddressId());
        order.setOrderName(orderVO.getOrderName());
        order.setCustomerId(orderVO.getCustomerId());
        order.setMaterialId(orderVO.getMaterialId());
        order.setModeId(orderVO.getModeId());
        order.setOrderNumber(orderVO.getOrderNumber());
        order.setOrderColor(orderVO.getOrderColor());
        order.setBattenVersionId(orderVO.getBattenVersionId());
        order.setBattenBigId(orderVO.getBattenBigId());
        order.setBattenSmallId(orderVO.getBattenSmallId());
        order.setOrderPrice(orderVO.getOrderPrice());
        order.setOrderTotal(orderVO.getOrderTotal());
        order.setOrderState(0);
        order.setRemarks(orderVO.getRemarks());
        if (orderVO.getImgUrl() != null){
            switch (orderVO.getImgUrl().length) {
                case 1:
                    order.setDemandPic1(orderVO.getImgUrl()[0]);
                    break;
                case 2:
                    order.setDemandPic1(orderVO.getImgUrl()[0]);
                    order.setDemandPic2(orderVO.getImgUrl()[1]);
                    break;
                case 3:
                    order.setDemandPic1(orderVO.getImgUrl()[0]);
                    order.setDemandPic2(orderVO.getImgUrl()[1]);
                    order.setDemandPic3(orderVO.getImgUrl()[2]);
                    break;
                case 4:
                    order.setDemandPic1(orderVO.getImgUrl()[0]);
                    order.setDemandPic2(orderVO.getImgUrl()[1]);
                    order.setDemandPic3(orderVO.getImgUrl()[2]);
                    order.setDemandPic4(orderVO.getImgUrl()[3]);
                    break;
                case 5:
                    order.setDemandPic1(orderVO.getImgUrl()[0]);
                    order.setDemandPic2(orderVO.getImgUrl()[1]);
                    order.setDemandPic3(orderVO.getImgUrl()[2]);
                    order.setDemandPic4(orderVO.getImgUrl()[3]);
                    order.setDemandPic5(orderVO.getImgUrl()[4]);
                    break;
            }
        }
        Integer result = orderMapper.saveOrderInfo(order);
        return result;
    }

    /**
     * 修改订单信息
     *
     * @param orderVO
     * @return
     */
    @Override
    public Integer updateOrderInfo(OrderVO orderVO) {
        OrderInfo order = new OrderInfo();
        order.setOrderId(orderVO.getOrderId());
        order.setAddressId(orderVO.getAddressId());
        order.setOrderName(orderVO.getOrderName());
        order.setCustomerId(orderVO.getCustomerId());
        order.setMaterialId(orderVO.getMaterialId());
        order.setModeId(orderVO.getModeId());
        order.setOrderNumber(orderVO.getOrderNumber());
        order.setOrderColor(orderVO.getOrderColor());
        order.setBattenVersionId(orderVO.getBattenVersionId());
        order.setBattenBigId(orderVO.getBattenBigId());
        order.setBattenSmallId(orderVO.getBattenSmallId());
        order.setOrderPrice(orderVO.getOrderPrice());
        order.setOrderTotal(orderVO.getOrderTotal());
        order.setRemarks(orderVO.getRemarks());
        Integer result = orderMapper.updateOrderInfo(order);
        return result;
    }

    /**
     * 删除指定orderId订单信息
     *
     * @param orderId
     * @return
     */
    @Override
    public Integer deleteOrderInfoById(Long orderId) {
        List<Logistics> allLogistics = null;
        for (OrderInfo info : allOrder) {
            if (orderId.equals(info.getOrderId())) {
                if (info.getSendDate() != null) {
                    allLogistics = logisticsMapper.getAllLogistics(null, null, null, null);
                    for (Logistics logistics : allLogistics) {
                        if (orderId.equals(logistics.getOrderId())) {
                            logisticsMapper.deleteLogisticsInfoById(logistics.getLogisticsId());
                        }
                    }
                    Integer result = orderMapper.deleteOrderInfoById(orderId);
                    return result;
                } else {
                    Integer result = orderMapper.deleteOrderInfoById(orderId);
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 批量删除指定id的订单信息
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public Integer deleteMoreOrderInfoById(Long[] ids) {
        List<Logistics> allLogistics = null;
        Integer result = null;
        for (int i = 0; i < ids.length; i++) {
            for (OrderInfo info : allOrder) {
                if (ids[i].equals(info.getOrderId())) {
                    if (info.getSendDate() == null) {
                        System.out.println(980910);
                        try {
                            result = orderMapper.deleteOrderInfoById(ids[i]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        allLogistics = logisticsMapper.getAllLogistics(null, null, null, null);
                        for (Logistics logistics : allLogistics) {    // 查询全部的物流信息
                            if (ids[i].equals(logistics.getOrderId())) {
                                // System.out.println(true);
                                try {
                                    logisticsMapper.deleteLogisticsInfoById(logistics.getLogisticsId());
                                    result = orderMapper.deleteOrderInfoById(ids[i]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 订单完工
     *
     * @param orderId
     * @return
     */
    @Override
    public Integer setOrderInfoByState(Long orderId) {
        Integer integer = orderMapper.setOrderInfoByState(orderId);
        return integer;
    }

    /**
     * 修改订单发货日期
     *
     * @param created
     * @return
     */
    @Override
    public Integer setOrderInfoBySendDate(Date created, Long orderId) {
        Integer integer = orderMapper.setOrderInfoBySendDate(created, orderId);
        return integer;
    }


    @Resource
    private ReworkMapper reworkMapper;
    /**
     * 订单返工
     *
     * @param orderVO 选中的订单id
     * @return SaveUtil
     */
    @Override
    public Integer setOrderInfoByReturn(OrderVO orderVO) {
        Integer result = orderMapper.setOrderInfoByReturn(orderVO.getOrderId());
        Rework rework = new Rework();
        rework.setOrderId(orderVO.getOrderId());
        rework.setOrderName(orderVO.getOrderName());
        Integer results = reworkMapper.saveReworkOrderInfo(rework);
        if (result > 0 && results > 0) {
            return result;
        }else {
            return 0;
        }
    }

    @Override
    public OrderVO getOrderInfoByAllId(Long orderId) {
        for (OrderVO orderInfo:orderVO){
            if (orderInfo.getOrderId().equals(orderId)){
                return orderInfo;
            }
        }
        return null;
    }
}

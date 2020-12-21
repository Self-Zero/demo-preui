package com.oac.project.system.rework.service.impl;

import com.oac.project.system.batten.domain.BattenType;
import com.oac.project.system.batten.domain.BattenVersion;
import com.oac.project.system.batten.mapper.BattenTypeMapper;
import com.oac.project.system.city.domain.City;
import com.oac.project.system.city.mapper.CityMapper;
import com.oac.project.system.customer.domain.Customer;
import com.oac.project.system.customer.mapper.CustomerMapper;
import com.oac.project.system.material.domain.Material;
import com.oac.project.system.material.mapper.MaterialMapper;
import com.oac.project.system.mode.domain.Mode;
import com.oac.project.system.mode.mapper.ModeMapper;
import com.oac.project.system.order.domain.OrderInfo;
import com.oac.project.system.order.mapper.OrderMapper;
import com.oac.project.system.order.vo.OrderVO;
import com.oac.project.system.rework.mapper.ReworkMapper;
import com.oac.project.system.rework.service.ReworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * ReworkServiceImpl 业务操作
 */
@Service
public class ReworkServiceImpl implements ReworkService {

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
    private OrderMapper reworkOrderMapper;

    private List<OrderInfo> reworks = null;
    private List<BattenType> allBattenType = null;
    private List<BattenVersion> allBattenVersion = null;
    private List<Customer> allCustomer = null;
    private List<City> allCity = null;
    private List<Material> allMaterial = null;
    private List<Mode> allMode = null;
    private List<OrderVO> orderVO = null;

    /**
     * 分页查询全部的订单返工信息
     * @param page  当前页数
     * @param limit 显示条数
     * @param customerCondition 客户姓名条件
     * @param orderCondition    订单名称条件
     * @return  PageUtil<Rework>
     */
    @Override
    public List<OrderVO> getAllReworkOrderInfo(Integer page, Integer limit, String customerCondition, String orderCondition) {
        // page处理
        if (page != null) {
            page = (page - 1) * limit;
        }
        // 声明VO的集合
        orderVO = new ArrayList<>();
        // 声明vo对象
        OrderVO vo = null;
        // 声明日期转换格式的对象及引用
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月dd日-----HH:mm:ss");
        reworks = reworkOrderMapper.getAllReworkOrderInfo(page, limit, customerCondition, orderCondition);
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
        for (OrderInfo order : reworks) {
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
     * 根据条件筛选查询数据的条数
     * @param customerCondition
     * @param orderCondition
     * @return Long
     */
    @Override
    public Long getCount(String customerCondition, String orderCondition) {
        Long result = reworkOrderMapper.getReworkCount(customerCondition, orderCondition);
        return result;
    }
}

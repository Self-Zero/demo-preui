package com.oac.project.system.logistics.mapper;

import com.oac.project.system.logistics.domain.Logistics;
import com.oac.project.system.logistics.vo.LogisticsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticsMapper {

    /**
     * 查询全部的物流发货信息
     *
     * @param page      当前页数
     * @param limit     显示条数
     * @param orderName 订单名称
     * @param sendDate  发货日期
     * @return
     */
    List<Logistics> getAllLogistics(Integer page, Integer limit, String orderName, String sendDate);

    /**
     * 根据Id查询相应的物流发货信息
     *
     * @param logisticsId 指定的物流发货信息ID
     * @return
     */
    Logistics getLogisticsById(Long logisticsId);

    /**
     * 查询全部物流发货信息的条数
     *
     * @return
     */
    Long getCount();

    /**
     * 添加物流发货信息
     *
     * @param logistics 输入的物流发货信息
     * @return
     */
    Integer saveLogisticsInfo(Logistics logistics);

    /**
     * 删除指定id的物流发货信息
     *
     * @param logisticsId 指定的物流发货信息ID
     * @return
     */
    Integer deleteLogisticsInfoById(Long logisticsId);

    /**
     * 编辑物流发货信息
     *
     * @param logistics 需要编辑的物流发货信息
     * @return
     */
    Integer updateLogisticsInfo(Logistics logistics);
}

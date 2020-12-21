package com.oac.project.system.rework.mapper;

import com.oac.project.system.rework.domain.Rework;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ReworkMapper 操作
 */
@Repository
public interface ReworkMapper {

    /**
     * 分页查询全部的订单返工信息
     * @param page  当前页数
     * @param limit 显示条数
     * @param customerCondition 客户姓名条件
     * @param orderCondition    订单名称条件
     * @return  PageUtil<Rework>
     */
    List<Rework> getAllReworkOrderInfo(Integer page, Integer limit, String customerCondition, String orderCondition);

    /**
     * 根据条件筛选查询数据的条数
     * @param customerCondition
     * @param orderCondition
     * @return Long
     */
    Long getCount(String customerCondition, String orderCondition);

    /**
     * 添加返工的订单信息
     * @param rework 需要添加的主体信息
     * @return  Integer
     */
    Integer saveReworkOrderInfo(Rework rework);
}

package com.oac.project.system.batten.mapper;

import com.oac.project.system.batten.domain.BattenInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BattenInfoMapper操作
 */
@Repository
public interface BattenInfoMapper {

    /**
     * 查询BattenInfo方料的信息
     *
     * @param page      当前页
     * @param limit     显示条数
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return List<BattenInfo>
     */
    List<BattenInfo> getAllBattenInfo(Integer page, Integer limit, String startDate, String endDate);

    /**
     * 查询BattenInfo条数
     *
     * @return Long
     */
    Long getCount(String startDate, String endDate);

    /**
     * 添加方料信息
     *
     * @param battenInfo 保存的方料信息主体
     * @return Integer
     */
    Integer saveBattenInfo(BattenInfo battenInfo);

    /**
     * 修改方料信息
     *
     * @param battenInfo 修改的方料信息主体
     * @return Integer
     */
    Integer updateBattenInfo(BattenInfo battenInfo);

    /**
     * 删除指定id的方料信息
     *
     * @param battenInfoId 选中的方料id
     * @return Integer
     */
    Integer deleteBattenInfoById(Long battenInfoId);

    /**
     * 批量删除选中id的方料信息
     *
     * @param ids 批量选中的方料id集合
     * @return Integer
     */
    Integer deleteMoreBattenInfoById(Integer[] ids);
}

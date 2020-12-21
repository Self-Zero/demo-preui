package com.oac.project.system.batten.service;

import com.oac.project.system.batten.domain.BattenType;
import com.oac.project.system.batten.domain.BattenVersion;
import com.oac.project.system.batten.vo.BattenVO;

import java.util.List;

/**
 * BattenService接口
 */
public interface BattenService {

    /**
     * 查询柱型信息（Z-01 --> Z-59）
     *
     * @return List<BattenVersion>
     */
    List<BattenVersion> getAllBattenVersion();

    /**
     * 查询方料规格信息
     *
     * @return List<BattenType>
     */
    List<BattenType> getBattenType();

    /**
     * 根据typeId查询方料规格信息(0小柱-1大柱)
     *
     * @param typeId 方料类型id
     * @return List<BattenType>
     */
    List<BattenType> getBattenTypeById(Integer typeId);

    /**
     * 查询Batten方料的信息
     *
     * @param page      当前页数
     * @param limit     显示条数
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return List<BattenVO>
     */
    List<BattenVO> getAllBattenInfo(Integer page, Integer limit, String startDate, String endDate);

    /**
     * 查询Batten条数
     *
     * @param startDate 根据开始日期查询
     * @param endDate   根据结束日期查询
     * @return Long
     */
    Long getCount(String startDate, String endDate);

    /**
     * 添加方料信息
     *
     * @param battenVO 需要保存的方料信息
     * @return Integer
     */
    Integer saveBattenInfo(BattenVO battenVO);

    /**
     * 修改方料信息
     *
     * @param battenVO 需要修改的方料信息
     * @return Integer
     */
    Integer updateBattenInfo(BattenVO battenVO);

    /**
     * 删除指定id的方料信息
     *
     * @param battenInfoId 指定的方料id
     * @return Integer
     */
    Integer deleteBattenInfoById(Long battenInfoId);

    /**
     * 批量删除选中id的方料信息
     *
     * @param ids 选中的方料id数组
     * @return Integer
     */
    Integer deleteMoreBattenInfoById(Integer[] ids);
}
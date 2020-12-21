package com.oac.project.system.batten.service.impl;

import com.oac.project.system.batten.domain.BattenInfo;
import com.oac.project.system.batten.domain.BattenType;
import com.oac.project.system.batten.domain.BattenVersion;
import com.oac.project.system.batten.mapper.BattenInfoMapper;
import com.oac.project.system.batten.mapper.BattenTypeMapper;
import com.oac.project.system.batten.service.BattenService;
import com.oac.project.system.batten.vo.BattenVO;
import com.oac.project.system.material.domain.Material;
import com.oac.project.system.material.mapper.MaterialMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * BattenService操作
 */
@Service
public class BattenServiceImpl implements BattenService {

    @Resource
    private BattenInfoMapper battenInfoMapper;
    @Resource
    private BattenTypeMapper battenTypeMapper;
    @Resource
    private MaterialMapper materialMapper;

    private List<BattenVersion> allBattenVersion = null;
    private List<BattenType> allBattenType = null;
    private List<BattenInfo> allBattenInfo = null;
    private List<Material> allMaterial = null;

    /**
     * 查询柱型信息（Z-01 --> Z-59）
     *
     * @return
     */
    @Override
    public List<BattenVersion> getAllBattenVersion() {
        allBattenVersion = battenTypeMapper.getAllBattenVersion();
        return allBattenVersion;
    }

    /**
     * 查询方料规格信息
     *
     * @return
     */
    @Override
    public List<BattenType> getBattenType() {
        BattenType batten = null;
        List<BattenType> battenType = new ArrayList<>();
        allBattenType = battenTypeMapper.getAllBattenType();
        for (BattenType battens : allBattenType) {
            batten = new BattenType();
            batten.setBattenTypeId(battens.getBattenTypeId());
            batten.setBattenName(battens.getBattenName());
            battenType.add(batten);
        }
        return battenType;
    }

    /**
     * 根据typeId查询方料规格信息(0小柱-1大柱)
     *
     * @param typeId
     * @return
     */
    @Override
    public List<BattenType> getBattenTypeById(Integer typeId) {
        BattenType battenType = null;
        List<BattenType> battenTypeById = new ArrayList<>();
        allBattenType = battenTypeMapper.getAllBattenType();
        for (BattenType batten : allBattenType) {
            battenType = new BattenType();
            if (batten.getBattenTypeName().equals(typeId)) {
                battenType.setBattenTypeId(batten.getBattenTypeId());
                battenType.setBattenName(batten.getBattenName());
                battenTypeById.add(battenType);
            }
        }
        return battenTypeById;
    }

    /**
     * 查询Batten方料的信息
     *
     * @return
     */
    @Override
    public List<BattenVO> getAllBattenInfo(Integer page, Integer limit, String startDate, String endDate) {
        if (page != null) {
            page = (page - 1) * limit;
        }
        BattenVO vo = null;
        // 转换日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月dd日-----HH:mm:ss");
        List<BattenVO> battenVO = new ArrayList<>();
        allBattenInfo = battenInfoMapper.getAllBattenInfo(page, limit, startDate, endDate);         // 所有的方料信息
        allBattenType = battenTypeMapper.getAllBattenType();                                        // 所有的方料类型
        allMaterial = materialMapper.getAllMaterial();                                              // 所有的材质
        String created = null;
        String updated = null;
        for (BattenInfo battenInfo : allBattenInfo) {
            // 声明BattenVO对象 将数据封装到BattenVO中 展示给前台页面
            vo = new BattenVO();
            vo.setBattenInfoId(battenInfo.getBattenInfoId());
            vo.setBattenNumber(battenInfo.getBattenNumber());
            vo.setBattenPrice(battenInfo.getBattenPrice());
            vo.setBattenTotal(battenInfo.getBattenTotal());
            vo.setRemarks(battenInfo.getRemarks());
            if (battenInfo.getCreated() != null) {
                created = sdf.format(battenInfo.getCreated());
                vo.setCreated(created);
            }
            if (battenInfo.getUpdated() != null) {
                updated = sdf.format(battenInfo.getUpdated());
                vo.setUpdated(updated);
            }
            for (BattenType battenType : allBattenType) {
                // 根据方料信息表中的typeId 和 方料类型表中的 typeId对比
                if (battenType.getBattenTypeId().equals(battenInfo.getBattenTypeId())) {
                    // id相同时 将id和name封装到BattenVO中
                    vo.setBattenTypeId(battenType.getBattenTypeId());
                    vo.setBattenName(battenType.getBattenName());
                }
            }
            for (Material material : allMaterial) {
                // 根据材质表中的typeId 和 方料类型表中的 typeId对比
                if (material.getMaterialId().equals(battenInfo.getMaterialId())) {
                    // id相同时 将id和name封装到BattenVO中
                    vo.setMaterialId(material.getMaterialId());
                    vo.setMaterialName(material.getMaterialName());
                }
            }
            battenVO.add(vo);
        }
        /*System.out.println("----------------------------------查询所有方料信息-------------------------------------------->");
        System.out.println(battenVO.size() + "条信息");
        System.out.println(battenVO);
        System.out.println("--------------------------------------------------------------------------------------------->");*/
        return battenVO;
    }

    /**
     * 查询Batten条数
     *
     * @return
     */
    @Override
    public Long getCount(String startDate, String endDate) {
        return battenInfoMapper.getCount(startDate,endDate);
    }

    /**
     * 添加方料信息
     *
     * @param battenVO
     * @return
     */
    @Override
    public Integer saveBattenInfo(BattenVO battenVO) {
        BattenInfo battenInfo = new BattenInfo();
        battenInfo.setMaterialId(battenVO.getMaterialId());
        battenInfo.setBattenTypeId(battenVO.getBattenTypeId());
        battenInfo.setBattenNumber(battenVO.getBattenNumber());
        battenInfo.setBattenPrice(battenVO.getBattenPrice());
        battenInfo.setBattenTotal(battenVO.getBattenTotal());
        battenInfo.setRemarks(battenVO.getRemarks());
        Integer result = battenInfoMapper.saveBattenInfo(battenInfo);
        return result;
    }

    /**
     * 修改方料信息
     *
     * @param battenVO
     * @return
     */
    @Override
    public Integer updateBattenInfo(BattenVO battenVO) {
        BattenInfo battenInfo = new BattenInfo();
        battenInfo.setBattenInfoId(battenVO.getBattenInfoId());
        battenInfo.setMaterialId(battenVO.getMaterialId());
        battenInfo.setBattenTypeId(battenVO.getBattenTypeId());
        battenInfo.setBattenNumber(battenVO.getBattenNumber());
        battenInfo.setBattenPrice(battenVO.getBattenPrice());
        battenInfo.setBattenTotal(battenVO.getBattenTotal());
        battenInfo.setRemarks(battenVO.getRemarks());
        Integer result = battenInfoMapper.updateBattenInfo(battenInfo);
        return result;
    }

    /**
     * 删除指定id的方料信息
     *
     * @param battenInfoId
     * @return
     */
    @Override
    public Integer deleteBattenInfoById(Long battenInfoId) {
        Integer result = battenInfoMapper.deleteBattenInfoById(battenInfoId);
        return result;
    }

    /**
     * 批量删除选中id的方料信息
     *
     * @param ids
     * @return
     */
    @Override
    public Integer deleteMoreBattenInfoById(Integer[] ids) {
        Integer result = battenInfoMapper.deleteMoreBattenInfoById(ids);
        return result;
    }
}

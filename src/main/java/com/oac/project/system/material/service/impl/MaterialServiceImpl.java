package com.oac.project.system.material.service.impl;

import com.oac.project.system.material.domain.Material;
import com.oac.project.system.material.mapper.MaterialMapper;
import com.oac.project.system.material.service.MaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Material操作
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Resource
    private MaterialMapper materialMapper;

    private List<Material> allMaterial = null;

    /**
     * 查询所有的材质信息
     *
     * @return
     */
    @Override
    public List<Material> getAllMaterial() {
        allMaterial = materialMapper.getAllMaterial();
        return allMaterial;
    }
}

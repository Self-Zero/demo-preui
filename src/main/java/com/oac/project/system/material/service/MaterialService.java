package com.oac.project.system.material.service;


import com.oac.project.system.material.domain.Material;

import java.util.List;

/**
 * Material操作
 */
public interface MaterialService {

    /**
     * 查询所有的材质信息
     *
     * @return
     */
    List<Material> getAllMaterial();

}

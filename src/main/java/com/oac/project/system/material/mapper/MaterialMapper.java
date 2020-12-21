package com.oac.project.system.material.mapper;

import com.oac.project.system.material.domain.Material;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialMapper {

    /**
     * 查询所有的Material材质信息
     *
     * @return
     */
    List<Material> getAllMaterial();

}

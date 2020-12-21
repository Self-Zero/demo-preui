package com.oac.project.system.batten.mapper;

import com.oac.project.system.batten.domain.BattenType;
import com.oac.project.system.batten.domain.BattenVersion;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BattenTypeMapper操作
 */
@Repository
public interface BattenTypeMapper {

    /**
     * 查询方料的类型
     *
     * @return List<BattenType>
     */
    List<BattenType> getAllBattenType();

    /**
     * 查询柱型的信息（Z-01 --> Z-59）
     *
     * @return List<BattenVersion>
     */
    List<BattenVersion> getAllBattenVersion();

}

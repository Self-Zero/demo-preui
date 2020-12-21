package com.oac.project.system.mode.mapper;

import com.oac.project.system.mode.domain.Mode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeMapper {

    /**
     * 查询所有的Mode类型信息
     *
     * @return
     */
    List<Mode> getAllMode();

}

package com.oac.project.system.mode.service;

import com.oac.project.system.mode.domain.Mode;

import java.util.List;

/**
 * Mode操作
 */
public interface ModeService {

    /**
     * 查询所有的Mode信息
     *
     * @return
     */
    List<Mode> getAllMode();
}

package com.oac.project.system.mode.service.impl;

import com.oac.project.system.mode.domain.Mode;
import com.oac.project.system.mode.mapper.ModeMapper;
import com.oac.project.system.mode.service.ModeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Mode操作
 */
@Service
public class ModeServiceImpl implements ModeService {

    @Resource
    private ModeMapper modeMapper;

    private List<Mode> allMode = null;

    /**
     * 查询所有的Mode信息
     *
     * @return
     */
    @Override
    public List<Mode> getAllMode() {
        allMode = modeMapper.getAllMode();
        return allMode;
    }
}

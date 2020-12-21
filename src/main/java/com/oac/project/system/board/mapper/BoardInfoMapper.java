package com.oac.project.system.board.mapper;

import com.oac.project.system.board.domain.BoardInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BoardInfoMapper操作
 */
@Repository
public interface BoardInfoMapper {

    /**
     * 查询Board板材的信息
     *
     * @return
     */
    List<BoardInfo> getAllBoardInfo(Integer page, Integer limit, String startDate, String endDate);

    /**
     * 查询Batten条数
     *
     * @return
     */
    Long getCount(String startDate, String endDate);

    /**
     * 查询Board板材的信息
     *
     * @param boardInfo
     * @return
     */
    Integer saveBoardInfo(BoardInfo boardInfo);

    /**
     * 修改板材信息
     *
     * @param boardInfo
     * @return
     */
    Integer updateBoardInfo(BoardInfo boardInfo) throws Exception;

    /**
     * 删除指定id的板材信息
     *
     * @param boardInfoId
     * @return
     */
    Integer deleteBoardInfoById(Long boardInfoId);

    /**
     * 批量删除选中id的板材信息
     *
     * @param ids
     * @return
     */
    Integer deleteMoreBoardInfoById(Integer[] ids);
}
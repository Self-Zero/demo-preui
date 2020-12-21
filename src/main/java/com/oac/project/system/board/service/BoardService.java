package com.oac.project.system.board.service;

import com.oac.project.system.board.domain.BoardType;
import com.oac.project.system.board.vo.BoardVO;

import java.util.List;

/**
 * BoardService接口
 */
public interface BoardService {

    /**
     * 查询板材类型
     *
     * @return
     */
    List<BoardType> getAllBoardType();

    /**
     * 查询Board板材的信息
     *
     * @return
     */
    List<BoardVO> getAllBoardInfo(Integer page, Integer limit, String startDate, String endDate);

    /**
     * 查询Batten条数
     *
     * @return
     */
    Long getCount(String startDate, String endDate);

    /**
     * 添加板材信息
     *
     * @param boardVO
     * @return
     */
    Integer saveBoardInfo(BoardVO boardVO);

    /**
     * 修改板材信息
     *
     * @param boardVO
     * @return
     */
    Integer updateBoardInfo(BoardVO boardVO);

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

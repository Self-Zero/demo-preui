package com.oac.project.system.board.mapper;

import com.oac.project.system.board.domain.BoardType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BoardType操作
 */
@Repository
public interface BoardTypeMapper {

    /**
     * 查询所有的板材类型
     *
     * @return
     */
    List<BoardType> getAllBoardType();
}

package com.oac.project.system.board.service.impl;

import com.oac.project.system.board.domain.BoardInfo;
import com.oac.project.system.board.domain.BoardType;
import com.oac.project.system.board.mapper.BoardInfoMapper;
import com.oac.project.system.board.mapper.BoardTypeMapper;
import com.oac.project.system.board.service.BoardService;
import com.oac.project.system.board.vo.BoardVO;
import com.oac.project.system.material.domain.Material;
import com.oac.project.system.material.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * BoardService操作
 */
@Service
public class BoardServiceImpl implements BoardService {

    @Resource
    private BoardInfoMapper boardInfoMapper;
    @Resource
    private BoardTypeMapper boardTypeMapper;
    @Resource
    private MaterialMapper materialMapper;

    private List<BoardType> allBoardType = null;
    private List<BoardInfo> allBoardInfo = null;
    private List<Material> allMaterial = null;

    /**
     * 查询板材类型
     *
     * @return
     */
    @Override
    public List<BoardType> getAllBoardType() {
        allBoardType = boardTypeMapper.getAllBoardType();
        return allBoardType;
    }

    /**
     * 查询Board板材的信息
     *
     * @return
     */
    @Override
    public List<BoardVO> getAllBoardInfo(Integer page, Integer limit, String startDate, String endDate) {
        if (page != null) {
            page = (page - 1) * limit;
        }
        BoardVO vo = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月dd日-----HH:mm:ss");
        List<BoardVO> boardVO = new ArrayList<>();
        allBoardInfo = boardInfoMapper.getAllBoardInfo(page, limit, startDate, endDate);       // 所有的板材信息
        allBoardType = boardTypeMapper.getAllBoardType();       // 所有的板材类型
        allMaterial = materialMapper.getAllMaterial();           // 所有的材质
        String created = null;
        String updated = null;
        for (BoardInfo boardInfo : allBoardInfo) {
            // 声明一个BoardVO对象 将数据封装到BoardVO中 展示给前台页面
            vo = new BoardVO();
            vo.setBoardInfoId(boardInfo.getBoardInfoId());
            vo.setBoardNumber(boardInfo.getBoardNumber());
            vo.setBoardPrice(boardInfo.getBoardPrice());
            vo.setBoardTotal(boardInfo.getBoardTotal());
            vo.setRemarks(boardInfo.getRemarks());
            // 转换日期格式
            created = sdf.format(boardInfo.getCreated());
            vo.setCreated(created);
            updated = sdf.format(boardInfo.getUpdated());
            vo.setUpdated(updated);
            for (BoardType boardType : allBoardType) {
                // 根据板材信息表中的typeId 和 板材类型表中的 typeId对比
                if (boardType.getBoardTypeId().equals(boardInfo.getBoardTypeId())) {
                    // id相同时 将id和name封装到BoardVO中
                    vo.setBoardTypeId(boardType.getBoardTypeId());
                    vo.setBoardName(boardType.getBoardName());
                }
            }
            for (Material material : allMaterial) {
                // 同理 将材质的信息封装到BoardVO中
                if (material.getMaterialId().equals(boardInfo.getMaterialId())) {
                    // id相同时 将id和name封装到BoardVO中
                    vo.setMaterialId(material.getMaterialId());
                    vo.setMaterialName(material.getMaterialName());
                }
            }
            boardVO.add(vo);
        }
        /*System.out.println("----------------------------------查询所有板材信息-------------------------------------------->");
        System.out.println(boardVO);
        System.out.println("--------------------------------------------------------------------------------------------->");*/
        return boardVO;
    }

    /**
     * 查询Batten条数
     *
     * @return
     */
    @Override
    public Long getCount(String startDate, String endDate) {
        return boardInfoMapper.getCount(startDate,endDate);
    }

    /**
     * 添加板材信息
     *
     * @param boardVO
     * @return
     */
    @Override
    public Integer saveBoardInfo(BoardVO boardVO) {
        int result = 0;
        // 读取系统中所有的BoardType
        allBoardType = boardTypeMapper.getAllBoardType();
        // 将VO中的数据提取到BoardInfo中
        BoardInfo boardInfo = new BoardInfo();
        boardInfo.setMaterialId(boardVO.getMaterialId());
        boardInfo.setBoardNumber(boardVO.getBoardNumber());
        boardInfo.setBoardPrice(boardVO.getBoardPrice());
        boardInfo.setBoardTotal(boardVO.getBoardTotal());
        boardInfo.setRemarks(boardVO.getRemarks());
        // 满足(typeId = 1 || typeId = 2)的同时满足 materialId = 1 进入提取数据 不满足 else
        if (((boardVO.getBoardTypeId() == 1 || boardVO.getBoardTypeId() == 2)) && boardVO.getMaterialId() == 1) {
            boardInfo.setBoardTypeId(boardVO.getBoardTypeId());
            // 同理 typeId = 3 && materialId = 2 进入提取数据 不满足 else
        } else if (boardVO.getBoardTypeId() == 3 && boardVO.getMaterialId() == 2) {
            boardInfo.setBoardTypeId(boardVO.getBoardTypeId());
            // 同理 typeId = 4 && materialId = 3 进入提取数据 不满足 else
        } else if (boardVO.getBoardTypeId() == 4 && boardVO.getMaterialId() == 3) {
            boardInfo.setBoardTypeId(boardVO.getBoardTypeId());
            // 同理 typeId = 5 && materialId = 4 进入提取数据 不满足 else
        } else if (boardVO.getBoardTypeId() == 5 && boardVO.getMaterialId() == 4) {
            boardInfo.setBoardTypeId(boardVO.getBoardTypeId());
        }
        try {
            result = boardInfoMapper.saveBoardInfo(boardInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改板材信息
     *
     * @param boardVO
     * @return
     */
    @Override
    public Integer updateBoardInfo(BoardVO boardVO) {
        allBoardType = boardTypeMapper.getAllBoardType();
        BoardInfo boardInfo = new BoardInfo();
        boardInfo.setBoardInfoId(boardVO.getBoardInfoId());
        boardInfo.setMaterialId(boardVO.getMaterialId());
        boardInfo.setBoardNumber(boardVO.getBoardNumber());
        boardInfo.setBoardPrice(boardVO.getBoardPrice());
        boardInfo.setBoardTotal(boardVO.getBoardTotal());
        boardInfo.setRemarks(boardVO.getRemarks());
        // 满足(typeId = 1 || typeId = 2)的同时满足 materialId = 1 进入提取数据 不满足 else
        if (((boardVO.getBoardTypeId() == 1 || boardVO.getBoardTypeId() == 2)) && boardVO.getMaterialId() == 1) {
            boardInfo.setBoardTypeId(boardVO.getBoardTypeId());
            // 同理 typeId = 3 && materialId = 2 进入提取数据 不满足 else
        } else if (boardVO.getBoardTypeId() == 3 && boardVO.getMaterialId() == 2) {
            boardInfo.setBoardTypeId(boardVO.getBoardTypeId());
            // 同理 typeId = 4 && materialId = 3 进入提取数据 不满足 else
        } else if (boardVO.getBoardTypeId() == 4 && boardVO.getMaterialId() == 3) {
            boardInfo.setBoardTypeId(boardVO.getBoardTypeId());
            // 同理 typeId = 5 && materialId = 4 进入提取数据 不满足 else
        } else if (boardVO.getBoardTypeId() == 5 && boardVO.getMaterialId() == 4) {
            boardInfo.setBoardTypeId(boardVO.getBoardTypeId());
        }
        Integer result = 0;
        // System.out.println("boardInfo:" + boardInfo);
        try {
            result = boardInfoMapper.updateBoardInfo(boardInfo);
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println(e.getMessage());
        }
        // System.out.println("result" + result);
        return result;
    }

    /**
     * 删除指定id的板材信息
     *
     * @param boardInfoId
     * @return
     */
    @Override
    public Integer deleteBoardInfoById(Long boardInfoId) {
        Integer result = boardInfoMapper.deleteBoardInfoById(boardInfoId);
        return result;
    }

    /**
     * 批量删除选中id的板材信息
     *
     * @param ids
     * @return
     */
    @Override
    public Integer deleteMoreBoardInfoById(Integer[] ids) {
        Integer result = boardInfoMapper.deleteMoreBoardInfoById(ids);
        return result;
    }

}

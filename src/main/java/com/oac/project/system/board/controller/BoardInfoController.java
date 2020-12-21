package com.oac.project.system.board.controller;

import com.oac.project.common.utils.PageUtil;
import com.oac.project.common.utils.SaveUtil;
import com.oac.project.system.board.service.BoardService;
import com.oac.project.system.board.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Board API
 */
@RestController
@RequestMapping(value = "/system")
public class BoardInfoController {

    @Autowired
    private BoardService boardService;

    private List<BoardVO> allBoardInfo = null;

    /**
     * 查询Board板材的信息
     *
     * @param page       当前页数
     * @param limit      显示条数
     * @param startDates 起始日期
     * @param endDates   截至日期
     * @return
     */
    @RequestMapping(value = "/board/select")
    public PageUtil<BoardVO> getAllBoardInfo(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, String startDates, String endDates) {
        String startDate = null;
        if (startDates == "" || startDates == null) {
            startDate = null;
        } else {
            startDate = startDates;
        }
        String endDate = null;
        if (endDates == "" || endDates == null) {
            endDate = null;
        } else {
            endDate = endDates;
        }
        allBoardInfo = boardService.getAllBoardInfo(page, limit, startDate, endDate);
        PageUtil<BoardVO> jsonOrder = new PageUtil<>();
        jsonOrder.setCode(0);
        jsonOrder.setCount(boardService.getCount(startDate, endDate));
        jsonOrder.setMsg("");
        jsonOrder.setData(allBoardInfo);
        return jsonOrder;
    }


    /**
     * 添加板材信息
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/board/save", method = RequestMethod.POST)
    public SaveUtil saveBoardInfo(@RequestBody BoardVO boardVO) throws Exception {
        // System.out.println(battenVO);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = boardService.saveBoardInfo(boardVO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                saveUtil.setSuccess(true);
                saveUtil.setMsg("添加成功！");
            } else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统异常！");
            }
        }
        return saveUtil;
    }

    /**
     * 修改板材信息
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/board/update", method = RequestMethod.POST)
    public SaveUtil updateBoardInfo(@RequestBody BoardVO boardVO) throws Exception {
        System.out.println(boardVO);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = boardService.updateBoardInfo(boardVO);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (result > 0) {
                saveUtil.setSuccess(true);
                saveUtil.setMsg("编辑成功！");
            } else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统异常！");
            }
        }
        return saveUtil;
    }


    /**
     * 删除指定id的板材信息
     *
     * @param boardInfoId
     * @return
     */
    @RequestMapping(value = "/board/remove/{boardInfoId}")
    public SaveUtil deleteBoardInfoById(@PathVariable("boardInfoId") Long boardInfoId) throws Exception {
        System.out.println(boardInfoId);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = boardService.deleteBoardInfoById(boardInfoId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                saveUtil.setSuccess(true);
                saveUtil.setMsg("删除成功！");
            } else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统异常！");
            }
        }
        return saveUtil;
    }

    /**
     * 批量删除选中id的板材信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/board/batchRemove/{ids}")
    public SaveUtil deleteMoreBoardInfoById(@PathVariable("ids") Integer[] ids) throws Exception {
        /*for (Integer id : ids) {
            System.out.println(id);
        }*/
        SaveUtil saveUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = boardService.deleteMoreBoardInfoById(ids);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                saveUtil.setSuccess(true);
                saveUtil.setMsg("删除成功！");
            } else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统异常！");
            }
        }
        return saveUtil;
    }

}

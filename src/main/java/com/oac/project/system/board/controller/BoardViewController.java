package com.oac.project.system.board.controller;

import com.oac.project.system.board.domain.BoardType;
import com.oac.project.system.board.service.BoardService;
import com.oac.project.system.board.vo.BoardVO;
import com.oac.project.system.material.domain.Material;
import com.oac.project.system.material.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Board跳转编辑 API
 */
@Controller
@RequestMapping(value = "/view")
public class BoardViewController {

    @Autowired
    private MaterialService materialService;
    @Autowired
    private BoardService boardService;

    private List<Material> allMaterial = null;
    private List<BoardType> boardTypes = null;
    private List<BoardVO> allBoardInfo = null;

    /**
     * 跳转BoardAdd页面并加载信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/board/add")
    public String disToPageBoardAdd(Model model) {
        allMaterial = materialService.getAllMaterial();
        model.addAttribute("allMaterial", allMaterial);
        boardTypes = boardService.getAllBoardType();
        model.addAttribute("boardType", boardTypes);
        return "view/information/board/operate/add";
    }

    /**
     * 跳转BoardEdit页面并加载信息
     *
     * @param model
     * @param boardInfoId 根据选中的boardInfoId回显数据
     * @return
     */
    @RequestMapping(value = "/system/board/edit")
    public String disToPageBoardEdit(Model model, @RequestParam("boardInfoId") Long boardInfoId) {
        allMaterial = materialService.getAllMaterial();
        model.addAttribute("allMaterial", allMaterial);
        boardTypes = boardService.getAllBoardType();
        model.addAttribute("boardType", boardTypes);
        allBoardInfo = boardService.getAllBoardInfo(null, null, null, null);
        for (BoardVO boardVO : allBoardInfo) {
            if (boardInfoId.equals(boardVO.getBoardInfoId())) {
                model.addAttribute("board", boardVO);
            }
        }
        return "view/information/board/operate/edit";
    }

}

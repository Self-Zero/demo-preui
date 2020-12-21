package com.oac.project.system.batten.controller;

import com.oac.project.system.batten.domain.BattenType;
import com.oac.project.system.batten.service.BattenService;
import com.oac.project.system.batten.vo.BattenVO;
import com.oac.project.system.material.domain.Material;
import com.oac.project.system.material.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * BattenView API
 */
@Controller
@RequestMapping(value = "/view")
public class BattenViewController {

    @Resource
    private MaterialService materialService;
    @Resource
    private BattenService battenService;

    private List<Material> allMaterial = null;
    private List<BattenType> battenType = null;
    private List<BattenVO> allBattenInfo = null;

    /**
     * 跳转BattenAdd页面并加载信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/batten/add")
    public String disToPageBattenAdd(Model model) {
        allMaterial = materialService.getAllMaterial();
        model.addAttribute("allMaterial", allMaterial);
        battenType = battenService.getBattenType();
        model.addAttribute("battenType", battenType);
        return "view/information/batten/operate/add";
    }

    /**
     * 跳转BattenEdit页面并加载信息
     *
     * @param model
     * @param battenInfoId 根据选中的battenInfoId回显数据
     * @return
     */
    @RequestMapping(value = "/system/batten/edit")
    public String disToPageBattenEdit(Model model, @RequestParam("battenInfoId") Long battenInfoId) {
        allMaterial = materialService.getAllMaterial();
        model.addAttribute("allMaterial", allMaterial);
        battenType = battenService.getBattenType();
        model.addAttribute("battenType", battenType);
        allBattenInfo = battenService.getAllBattenInfo(null, null, null, null);
        for (BattenVO battenVO : allBattenInfo) {
            if (battenInfoId.equals(battenVO.getBattenInfoId())) {
                model.addAttribute("batten", battenVO);
            }
        }
        return "view/information/batten/operate/edit";
    }

}

package com.oac.project.system.logistics.controller;

import com.oac.project.system.logistics.domain.Logistics;
import com.oac.project.system.logistics.domain.LogisticsCompany;
import com.oac.project.system.logistics.mapper.LogisticsCompanyMapper;
import com.oac.project.system.logistics.service.LogisticsService;
import com.oac.project.system.logistics.vo.LogisticsVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/view")
public class LogisticsViewController {

    @Resource
    private LogisticsService logisticsService;
    @Resource
    private LogisticsCompanyMapper logisticsCompanyMapper;

    /**
     * 跳转到发货信息编辑页面 并加载信息
     *
     * @param logisticsId
     * @return
     */
    @RequestMapping(value = "/system/logistics/edit")
    public String disToPageLogisticsEdit(Model model,@RequestParam("logisticsId") Long logisticsId) {
        LogisticsVO vo = logisticsService.getLogisticsById(logisticsId);
        List<LogisticsCompany> allLogisticsCompany = logisticsCompanyMapper.getAllLogisticsCompany();
        if (logisticsId.equals(vo.getLogisticsId())) {
            model.addAttribute("logistics", vo);
        }
        model.addAttribute("allLogisticsCompany", allLogisticsCompany);
        return "view/customers/send/operate/edit";
    }
}

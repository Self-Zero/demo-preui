package com.oac.project.system.order.controller;

import com.oac.project.common.utils.Constants;
import com.oac.project.system.batten.domain.BattenType;
import com.oac.project.system.batten.domain.BattenVersion;
import com.oac.project.system.batten.service.BattenService;
import com.oac.project.system.city.domain.City;
import com.oac.project.system.city.service.CityService;
import com.oac.project.system.distribution.domain.OrderDistribution;
import com.oac.project.system.distribution.service.DistributionService;
import com.oac.project.system.logistics.domain.LogisticsCompany;
import com.oac.project.system.logistics.mapper.LogisticsCompanyMapper;
import com.oac.project.system.material.domain.Material;
import com.oac.project.system.material.service.MaterialService;
import com.oac.project.system.mode.domain.Mode;
import com.oac.project.system.mode.service.ModeService;
import com.oac.project.system.order.domain.OrderInfo;
import com.oac.project.system.order.service.OrderService;
import com.oac.project.system.order.vo.OrderVO;
import com.oac.project.system.worker.domain.Worker;
import com.oac.project.system.worker.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Order跳转编辑 API
 */
@Controller
@RequestMapping(value = "/view")
public class OrderViewController {

    private static final Integer BIG_BATTEN_TYPE = 1;
    private static final Integer SMLLL_BATTEN_TYPE = 0;

    @Resource
    private CityService cityService;
    @Resource
    private MaterialService materialService;
    @Resource
    private ModeService modeService;
    @Resource
    private BattenService battenService;
    @Resource
    private OrderService orderService;
    @Resource
    private LogisticsCompanyMapper logisticsCompanyMapper;

    private List<Material> allMaterial = null;
    private List<City> cityByParent = null;
    private List<Mode> allMode = null;
    private List<BattenVersion> allBattenVersion = null;
    private List<BattenType> battenByTypeBig = null;
    private List<BattenType> battenByTypeSmall = null;
    private OrderVO orderVO = null;

    /**
     * 跳转OrderAdd页面并加载信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/order/add")
    public String disToPageOrderAdd(Model model) {
        allMaterial = materialService.getAllMaterial();
        cityByParent = cityService.getCityByParent();
        allMode = modeService.getAllMode();
        allBattenVersion = battenService.getAllBattenVersion();
        battenByTypeBig = battenService.getBattenTypeById(BIG_BATTEN_TYPE);
        battenByTypeSmall = battenService.getBattenTypeById(SMLLL_BATTEN_TYPE);
        model.addAttribute("cityByPatent", cityByParent);
        model.addAttribute("allMaterial", allMaterial);
        model.addAttribute("allMode", allMode);
        model.addAttribute("battenVersion", allBattenVersion);
        model.addAttribute("battenByTypeBig", battenByTypeBig);
        model.addAttribute("battenByTypeSmall", battenByTypeSmall);
        return "view/information/order/operate/add";
    }

    @Resource
    private DistributionService distributionService;
    /**
     * 跳转OrderEdit页面并加载信息
     *
     * @param model
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/system/order/edit")
    public String disToPageOrderEdit(Model model, @RequestParam("orderId") Long orderId) {
        orderVO = orderService.getOrderInfoByAllId(orderId);
        model.addAttribute("orderInfo", orderVO);
        allMaterial = materialService.getAllMaterial();
        cityByParent = cityService.getCityByParent();
        allMode = modeService.getAllMode();
        allBattenVersion = battenService.getAllBattenVersion();
        battenByTypeBig = battenService.getBattenTypeById(BIG_BATTEN_TYPE);
        battenByTypeSmall = battenService.getBattenTypeById(SMLLL_BATTEN_TYPE);
        OrderDistribution orderDistribution = distributionService.getOrderDistributionById(orderId);
        model.addAttribute("orderDistribution",orderDistribution);
        model.addAttribute("cityByPatent", cityByParent);
        model.addAttribute("allMaterial", allMaterial);
        model.addAttribute("allMode", allMode);
        model.addAttribute("battenVersion", allBattenVersion);
        model.addAttribute("battenByTypeBig", battenByTypeBig);
        model.addAttribute("battenByTypeSmall", battenByTypeSmall);
        return "view/information/order/operate/edit";
    }


    @RequestMapping(value = "/system/order/send")
    public String disToPageOrderSend(Model model, @RequestParam("orderId") Long orderId) {
        // System.out.println(orderId);
        List<LogisticsCompany> allLogisticsCompany = logisticsCompanyMapper.getAllLogisticsCompany();
        model.addAttribute("allLogisticsCompany",allLogisticsCompany);
        OrderInfo orderInfoById = orderService.getOrderInfoById(orderId);
        model.addAttribute("orderInfoById",orderInfoById);
        return "view/information/order/operate/send";
    }

    @RequestMapping(value = "/system/order/return")
    public String disToPageOrderReturn(Model model, @RequestParam("orderId") Long orderId) {
        System.out.println(orderId);
        OrderInfo orderInfoById = orderService.getOrderInfoById(orderId);
        System.out.println(orderInfoById);
        model.addAttribute("orderInfo",orderInfoById);


        return "view/information/order/operate/return";
    }

    @Resource
    private WorkerService workerService;

    @RequestMapping(value = "/system/order/distribution")
    public String disToPageOrderDistribution(Model model, @RequestParam("orderId") Long orderId) {
        List<Worker> carpentry = workerService.getWorkerInfoByType(Constants.WorkerType.WORKER_CARPENTRY.getValue());
        List<Worker> painter = workerService.getWorkerInfoByType(Constants.WorkerType.WORKER_PAINTER.getValue());
        List<Worker> primer = workerService.getWorkerInfoByType(Constants.WorkerType.WORKER_PRIMER_ID.getValue());
        model.addAttribute("orderId",orderId);
        model.addAttribute("carpentry",carpentry);
        model.addAttribute("painter",painter);
        model.addAttribute("primer",primer);
        return "view/information/order/operate/distribution";
    }

    /**
     * 第二级联动 根据省份的id查询对应的城市信息
     *
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/city/cityByParent")
    public @ResponseBody List<City> getCityByUrban(@RequestParam("parentId") Long parentId) {
        List<City> cityByUrban = cityService.getCityByUrban(parentId);
        return cityByUrban;
    }
}

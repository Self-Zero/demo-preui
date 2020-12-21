package com.oac.project.system.distribution.controller;

import com.oac.project.common.utils.Constants;
import com.oac.project.system.distribution.domain.OrderDistribution;
import com.oac.project.system.distribution.service.DistributionService;
import com.oac.project.system.worker.domain.Worker;
import com.oac.project.system.worker.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping(value = "/view")
@Controller
public class DistributionViewController {

    @Resource
    private WorkerService workerService;
    @Resource
    private DistributionService distributionService;

    /**
     * 跳转DistributionEdit页面并加载信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/distribution/edit")
    public String disToPageDistributionEdit(Model model,@RequestParam("orderId") Long orderId) {
        OrderDistribution orderDistributionById = distributionService.getOrderDistributionById(orderId);
        List<Worker> carpentry = workerService.getWorkerInfoByType(Constants.WorkerType.WORKER_CARPENTRY.getValue());
        List<Worker> painter = workerService.getWorkerInfoByType(Constants.WorkerType.WORKER_PAINTER.getValue());
        List<Worker> primer = workerService.getWorkerInfoByType(Constants.WorkerType.WORKER_PRIMER_ID.getValue());
        model.addAttribute("orderDistributionById",orderDistributionById);
        model.addAttribute("orderId",orderId);
        model.addAttribute("carpentry",carpentry);
        model.addAttribute("painter",painter);
        model.addAttribute("primer",primer);
        return "view/maintain/scatter/operate/edit";
    }

}

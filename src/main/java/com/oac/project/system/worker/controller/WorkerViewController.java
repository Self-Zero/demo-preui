package com.oac.project.system.worker.controller;

import com.oac.project.system.worker.domain.Worker;
import com.oac.project.system.worker.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/view")
public class WorkerViewController {

    @Resource
    private WorkerService workerService;

    @RequestMapping(value = "/system/worker/add")
    public String disToPageWorkerAdd(){
        return "view/system/worker/operate/add";
    }

    @RequestMapping(value = "/system/worker/edit")
    public String disToPageWorkerEdit(@RequestParam("workerId") Long workerId,
                                      Model model){
        Worker worker = workerService.getWorkerById(workerId);
        model.addAttribute("worker",worker);
        return "view/system/worker/operate/edit";
    }
}

package com.oac.project.system.worker.controller;

import com.oac.project.common.utils.PageUtil;
import com.oac.project.common.utils.SaveUtil;
import com.oac.project.system.order.vo.OrderVO;
import com.oac.project.system.worker.domain.Worker;
import com.oac.project.system.worker.service.WorkerService;
import jdk.nashorn.internal.objects.NativeUint8Array;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.management.relation.RoleUnresolved;
import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping(value = "/system")
public class WorkerController {

    @Resource
    private WorkerService workerService;

    /**
     * 分页查询全部的工人信息（也可以根据条件查询相应的工人信息）
     *
     * @param page                  当前页数
     * @param limit                 显示条数
     * @param workerNameParam       工人名称
     * @param entryDateParam        入职日期
     * @return
     */
    @RequestMapping(value = "/worker/select")
    public PageUtil<Worker> allWorker(@RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit,
                                      String workerNameParam,
                                      String entryDateParam){
        String workerNameCondition = "";
        if (workerNameParam == "" || workerNameParam == null) {
            workerNameCondition = null;
        } else {
            workerNameCondition = workerNameParam;
        }
        String entryDateCondition = "";
        if (entryDateParam == "" || entryDateParam == null) {
            entryDateCondition = null;
        } else {
            entryDateCondition = entryDateParam;
        }
        PageUtil<Worker> jsonWorker = new PageUtil<>();
        Long count = 0L;
        List<Worker> workerInfo = null;
        try {
            // 分页查询全部的工人信息（也可以根据条件查询相应的工人信息）
            workerInfo = workerService.getAllWorkerInfo(page, limit, workerNameCondition, entryDateCondition);
            count = workerService.getCount(workerNameCondition, entryDateCondition);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workerInfo != null) {
                jsonWorker.setCode(0);
                jsonWorker.setCount(count);
                jsonWorker.setData(workerInfo);
            } else {
                jsonWorker.setCode(1);
                jsonWorker.setCount(count);
                jsonWorker.setData(null);
            }
        }
        // long endTime = System.currentTimeMillis();    //获取结束时间
        // System.out.println("controller程序运行时间：" + (endTime - startTime) + "ms");
        return jsonWorker;
    }

    /**
     * 添加员工信息 -- 入职
     * @param worker
     * @return
     */
    @RequestMapping(value = "/worker/save" ,method = RequestMethod.POST)
    public SaveUtil saveWorker(@RequestBody Worker worker){
        // System.out.println(worker);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = null;
        try {
            result = workerService.saveWorker(worker);
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally {
            if (result != null){
                saveUtil.setSuccess(true);
                saveUtil.setMsg("添加成功！");
            }else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统出现异常，请及时联系管理员修复！");
            }
        }
        return saveUtil;
    }

    /**
     * 修改员工信息
     * @param worker
     * @return
     */
    @RequestMapping(value = "/worker/update" ,method = RequestMethod.POST)
    public SaveUtil updateWorker(@RequestBody Worker worker){
        // System.out.println(worker);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = null;
        try {
            result = workerService.updateWorker(worker);
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally {
            if (result != null){
                saveUtil.setSuccess(true);
                saveUtil.setMsg("修改成功！");
            }else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统出现异常，请及时联系管理员修复！");
            }
        }
        return saveUtil;
    }

    /**
     * 员工离职
     * @param worker
     * @return
     */
    @RequestMapping(value = "/worker/update/state" ,method = RequestMethod.POST)
    public SaveUtil updateWorkerByState(@RequestBody Worker worker){
        // System.out.println(worker);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = null;
        try {
            result = workerService.updateWorkerByState(worker);
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally {
            if (result != null){
                saveUtil.setSuccess(true);
                saveUtil.setMsg("员工已离职");
            }else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统出现异常，请及时联系管理员修复！");
            }
        }
        return saveUtil;
    }

    /**
     * 员工复职
     * @param worker
     * @return
     */
    @RequestMapping(value = "/worker/update/come" ,method = RequestMethod.POST)
    public SaveUtil updateWorkerByCome(@RequestBody Worker worker){
        // System.out.println(worker);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = null;
        try {
            result = workerService.updateWorkerByCome(worker);
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally {
            if (result != null){
                saveUtil.setSuccess(true);
                saveUtil.setMsg("员工已复职");
            }else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统出现异常，请及时联系管理员修复！");
            }
        }
        return saveUtil;
    }

    /**
     * 删除员工信息
     * @param workerId
     * @return
     */
    @RequestMapping(value = "/worker/remove/{workerId}" ,method = RequestMethod.POST)
    public SaveUtil deleteWorkerById(@PathVariable("workerId") Long workerId){
        // System.out.println(worker);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = null;
        try {
            result = workerService.deleteWorkerById(workerId);
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally {
            if (result != null){
                saveUtil.setSuccess(true);
                saveUtil.setMsg("删除成功");
            }else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统出现异常，请及时联系管理员修复！");
            }
        }
        return saveUtil;
    }
}

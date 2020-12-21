package com.oac.project.system.worker.service.impl;

import com.oac.project.common.utils.Constants;
import com.oac.project.system.worker.domain.Worker;
import com.oac.project.system.worker.mapper.WorkerMapper;
import com.oac.project.system.worker.service.WorkerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * WorkerService 业务操作
 */
@Service
public class WorkerServiceImpl implements WorkerService {

    @Resource
    private WorkerMapper workerMapper;

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private List<Worker> workerList = null;
    /**
     * 分页查询全部的工人信息（也可以根据条件查询相应的工人信息）
     *
     * @param page                  当前页数
     * @param limit                 显示条数
     * @param workerNameCondition   工人名称
     * @param entryDateCondition    入职日期
     * @return
     */
    @Override
    public List<Worker> getAllWorkerInfo(Integer page, Integer limit, String workerNameCondition, String entryDateCondition) {
        // page处理
        if (page != null) {
            page = (page - 1) * limit;
        }

        workerList = workerMapper.getAllWorkerInfo(page, limit, workerNameCondition, entryDateCondition);

        /*for (Worker worker:workerList){
            String format = FORMAT.format(worker.getEntryDate());
            worker.setEntryDate();

        }*/


        return workerList;
    }

    /**
     * 根据工种查询员工信息 --- 查询在职员工，已离职员工不可查询
     * @param typeId
     * @return
     */
    @Override
    public List<Worker> getWorkerInfoByType(Integer typeId) {
        List<Worker> workerList = workerMapper.getWorkerInfoByType(typeId);
        return workerList;
    }

    /**
     * 根据条件查询工人数量
     *
     * @param workerNameCondition   工人名称
     * @param entryDateCondition    入职日期
     * @return
     */
    @Override
    public Long getCount(String workerNameCondition, String entryDateCondition) {
        Long count = workerMapper.getCount(workerNameCondition, entryDateCondition);
        return count;
    }

    /**
     * 添加工人信息
     * @param worker 工人信息主体
     * @return
     */
    @Override
    public Integer saveWorker(Worker worker) {
        Integer result = workerMapper.saveWorker(worker);
        return result;
    }

    /**
     * 根据工人id查询工人信息
     * @param workerId 工人id
     * @return
     */
    @Override
    public Worker getWorkerById(Long workerId) {
        if (workerList != null){
        for (Worker worker : workerList) {
            if (workerId.equals(worker.getWorkerId())) {
                return worker;
            }
        }}else {
            Worker worker = workerMapper.getWorkerById(workerId);
            return worker;
        }
        return null;
    }

    /**
     * 修改员工信息
     * @param worker 员工信息主体
     * @return
     */
    @Override
    public Integer updateWorker(Worker worker) {
        Integer result = workerMapper.updateWorker(worker);
        return result;
    }

    /**
     * 员工离职
     * @param worker
     * @return
     */
    @Override
    public Integer updateWorkerByState(Worker worker) {
        Integer result = workerMapper.updateWorkerByState(worker);
        return result;
    }

    /**
     * 员工复职
     * @param worker
     * @return
     */
    @Override
    public Integer updateWorkerByCome(Worker worker) {
        Integer result = workerMapper.updateWorkerByCome(worker);
        return result;
    }

    /**
     * 删除员工信息
     * @param workerId
     * @return
     */
    @Override
    public Integer deleteWorkerById(Long workerId) {
        Integer result = workerMapper.deleteWorkerById(workerId);
        return result;
    }
}

package com.oac.project.system.worker.service;

import com.oac.project.common.utils.Constants;
import com.oac.project.system.worker.domain.Worker;

import java.util.List;

/**
 * WorkerService接口
 */
public interface WorkerService {

    /**
     * 分页查询全部的员工信息（也可以根据条件查询相应的员工信息）
     *
     * @param page                  当前页数
     * @param limit                 显示条数
     * @param workerNameCondition   员工名称
     * @param entryDateCondition    入职日期
     * @return
     */
    List<Worker> getAllWorkerInfo(Integer page, Integer limit, String workerNameCondition, String entryDateCondition);

    /**
     * 根据工种查询员工信息 --- 查询在职员工，已离职员工不可查询
     * @param typeId
     * @return
     */
    List<Worker> getWorkerInfoByType(Integer typeId);

    /**
     * 根据条件查询员工数量
     *
     * @param workerNameCondition   员工名称
     * @param entryDateCondition    入职日期
     * @return
     */
    Long getCount(String workerNameCondition, String entryDateCondition);

    /**
     * 添加员工信息
     * @param worker 员工信息主体
     * @return
     */
    Integer saveWorker(Worker worker);

    /**
     * 根据员工id查询员工信息
     * @param workerId 员工id
     * @return
     */
    Worker getWorkerById(Long workerId);

    /**
     * 修改员工信息
     * @param worker
     * @return
     */
    Integer updateWorker(Worker worker);

    /**
     * 员工离职
     * @param worker
     * @return
     */
    Integer updateWorkerByState(Worker worker);

    /**
     * 员工复职
     * @param worker
     * @return
     */
    Integer updateWorkerByCome(Worker worker);

    /**
     * 删除员工信息
     * @param workerId
     * @return
     */
    Integer deleteWorkerById(Long workerId);
}

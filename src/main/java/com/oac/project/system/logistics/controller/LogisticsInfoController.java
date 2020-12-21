package com.oac.project.system.logistics.controller;

import com.oac.project.common.utils.PageUtil;
import com.oac.project.common.utils.SaveUtil;
import com.oac.project.system.logistics.service.LogisticsService;
import com.oac.project.system.logistics.vo.LogisticsVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/system")
public class LogisticsInfoController {

    @Resource
    private LogisticsService logisticsService;

    /**
     * 查询全部的物流发货信息
     *
     * @param page           当前页数
     * @param limit          显示条数
     * @param orderNameParam 订单名称
     * @param sendDateParam  发货日期
     * @return
     */
    @RequestMapping(value = "/logistics/select")
    public PageUtil<LogisticsVO> getAllLogisticsInfo(@RequestParam("page") Integer page,
                                                     @RequestParam("limit") Integer limit,
                                                     String orderNameParam,
                                                     String sendDateParam) {
        // System.out.println(orderNameParam);
        // System.out.println(sendDateParam);
        // System.out.println(page);
        // System.out.println(limit);
        String orderName = "";              // 订单名称条件
        if (orderNameParam == "" || orderNameParam == null) {
            orderName = null;
        } else {
            orderName = orderNameParam;
        }
        String sendDate = "";                 // 订单发货时间条件
        if (sendDateParam == "" || sendDateParam == null) {
            sendDate = null;
        } else {
            sendDate = sendDateParam;
        }
        PageUtil<LogisticsVO> jsonLogistics = new PageUtil<>();
        List<LogisticsVO> logisticsInfo = null;
        try {
            logisticsInfo = logisticsService.getLogisticsInfo(page, limit, orderName, sendDate);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            jsonLogistics.setCode(0);
            jsonLogistics.setCount(logisticsService.getCount());
            jsonLogistics.setData(logisticsInfo);
            jsonLogistics.setMsg("");
        }
        return jsonLogistics;
    }

    /**
     * 删除指定id的物流发货信息
     *
     * @param logisticsId 指定的物流发货信息ID
     * @return
     */
    @RequestMapping(value = "/logistics/remove/{logisticsId}")
    public SaveUtil deleteLogisticsInfo(@PathVariable("logisticsId") Long logisticsId) {
        // System.out.println(logisticsId);
        SaveUtil logisticsUtil = new SaveUtil();
        Integer result = null;
        try {
            result = logisticsService.deleteLogisticsInfoById(logisticsId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                logisticsUtil.setSuccess(true);
                logisticsUtil.setMsg("删除成功");
            } else {
                logisticsUtil.setSuccess(false);
                logisticsUtil.setMsg("服务器异常啦");
            }
        }
        return logisticsUtil;
    }

    /**
     * 批量删除选中id的物流发货信息
     *
     * @param ids 批量删除选中的logisticsId
     * @return
     */
    @RequestMapping(value = "/logistics/batchRemove/{ids}")
    public SaveUtil deleteMoreLogisticsInfo(@PathVariable("ids") Long[] ids) {
        /*for (Long id : ids) {
            System.out.println(id);
        }*/
        SaveUtil logisticsUtil = new SaveUtil();
        Integer result = null;
        try {
            result = logisticsService.deleteMoreLogisticsInfo(ids);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(result>0){
                logisticsUtil.setSuccess(true);
                logisticsUtil.setMsg("删除成功");
            }else {
                logisticsUtil.setSuccess(false);
                logisticsUtil.setMsg("服务器开小差啦");
            }
        }
        return logisticsUtil;
    }

    /**
     * 编辑物流发货信息
     *
     * @param logisticsVO 需要编辑的物流发货信息
     * @return
     */
    @RequestMapping(value = "/logistics/save")
    public SaveUtil updateLogisticsInfo(@RequestBody LogisticsVO logisticsVO) {
        // System.out.println(logisticsVO);
        SaveUtil logisticsUtil = new SaveUtil();
        Integer result = null;
        try {
            result = logisticsService.updateLogisticsInfo(logisticsVO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0){
                logisticsUtil.setSuccess(true);
                logisticsUtil.setMsg("编辑成功");
            }else {
                logisticsUtil.setSuccess(false);
                logisticsUtil.setMsg("服务器开小差啦");
            }
        }
        return logisticsUtil;
    }
}

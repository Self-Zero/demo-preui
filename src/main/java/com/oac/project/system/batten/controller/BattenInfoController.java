package com.oac.project.system.batten.controller;

import com.oac.project.common.utils.PageUtil;
import com.oac.project.common.utils.SaveUtil;
import com.oac.project.system.batten.service.BattenService;
import com.oac.project.system.batten.vo.BattenVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * BattenInfo API
 */
@RestController
@RequestMapping(value = "/system")
public class BattenInfoController {

    @Resource
    private BattenService battenService;

    /**
     * 查询Batten方料的信息
     *
     * @param page       当前页数
     * @param limit      显示条数
     * @param startDates 开始日期
     * @param endDates   结束日期
     * @return List<BattenVO>
     */
    @RequestMapping(value = "/batten/select")
    public PageUtil<BattenVO> getAllBattenInfo(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, String startDates, String endDates) {
        // System.out.println(page);
        // System.out.println(limit);
        // System.out.println(startDates);
        // System.out.println(endDates);
        // 将startDates处理 防止属性="" 查询的信息错误
        String startDate = null;
        if (startDates == "" || startDates == null) {
            startDate = null;
        } else {
            startDate = startDates;
        }
        // 将endDates处理 防止属性="" 查询的信息错误
        String endDate = null;
        if (endDates == "" || endDates == null) {
            endDate = null;
        } else {
            endDate = endDates;
        }
        PageUtil<BattenVO> jsonOrder = new PageUtil<>();
        List<BattenVO> allBattenInfo = null;
        try {
            allBattenInfo = battenService.getAllBattenInfo(page, limit, startDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (allBattenInfo != null) {
                jsonOrder.setCode(0);
                jsonOrder.setCount(battenService.getCount(startDate, endDate));
                jsonOrder.setMsg("");
                jsonOrder.setData(allBattenInfo);
            } else {
                jsonOrder.setCode(1);
                jsonOrder.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return jsonOrder;
    }

    /**
     * 添加方料信息
     *
     * @param battenVO 保存的方料信息主体
     * @return SaveUtil
     */
    @RequestMapping(value = "/batten/save", method = RequestMethod.POST)
    public SaveUtil saveBattenInfo(@RequestBody BattenVO battenVO) {
        // System.out.println(battenVO);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = battenService.saveBattenInfo(battenVO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                saveUtil.setSuccess(true);
                saveUtil.setMsg("添加成功");
            } else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return saveUtil;
    }

    /**
     * 修改方料信息
     *
     * @param battenVO 修改的方料信息主体
     * @return SaveUtil
     */
    @RequestMapping(value = "/batten/update", method = RequestMethod.POST)
    public SaveUtil updateBattenInfo(@RequestBody BattenVO battenVO) {
        // System.out.println(battenVO);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = battenService.updateBattenInfo(battenVO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                saveUtil.setSuccess(true);
                saveUtil.setMsg("编辑成功");
            } else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return saveUtil;
    }

    /**
     * 删除指定id的方料信息
     *
     * @param battenInfoId 选中的方料id
     * @return SaveUtil
     */
    @RequestMapping(value = "/batten/remove/{battenInfoId}")
    public SaveUtil deleteBattenInfoById(@PathVariable("battenInfoId") Long battenInfoId) {
        // System.out.println(orderId);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = battenService.deleteBattenInfoById(battenInfoId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                saveUtil.setSuccess(true);
                saveUtil.setMsg("删除成功");
            } else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return saveUtil;
    }

    /**
     * 批量删除选中id的方料信息
     *
     * @param ids 批量选中的方料id集合
     * @return SaveUtil
     */
    @RequestMapping(value = "/batten/batchRemove/{ids}")
    public SaveUtil deleteMoreBattenInfoById(@PathVariable("ids") Integer[] ids) {
        /*for (Integer id : ids) {
            System.out.println(id);
        }*/
        SaveUtil saveUtil = new SaveUtil();
        Integer result = 0;
        try {
            result = battenService.deleteMoreBattenInfoById(ids);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                saveUtil.setSuccess(true);
                saveUtil.setMsg("批量删除成功");
            } else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统异常,请及时联系管理员进行修复!");
            }
        }
        return saveUtil;
    }

}

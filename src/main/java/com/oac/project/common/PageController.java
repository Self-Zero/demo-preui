package com.oac.project.common;

import com.oac.project.system.order.service.DataService;
import com.oac.project.system.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Controller
@RequestMapping(value = "/view")
public class PageController {

    @Resource
    private OrderService orderService;
    @Resource
    private DataService dataService;

    /**
     * 订单分析
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/console/console1")
    public String disToPageConsoleOne(Model model) {
        // 订单总量
        Long allCount = orderService.getCount(null, null, null, null, null);
        // 完成总量
        Long yesCount = orderService.getCount(1, null, null, null, null);
        // 未完成量
        Long noCount = orderService.getCount(0, null, null, null, null);
        // 返工总量
        Long reworkCount = orderService.getCount(null, "2015-1-5", null, null, null);
        model.addAttribute("allCount", allCount);
        model.addAttribute("yesCount", yesCount);
        model.addAttribute("noCount", noCount);
        model.addAttribute("reworkCount", reworkCount);
        return "view/console/console1";
    }

    /**
     * 资金统计
     *
     * @return
     */
    @RequestMapping(value = "/console/console2")
    public String disToPageConsoleTwo(Model model) {
        Long yearSumMoney = dataService.getYearSumMoney();
        Long lastYearSumMoney = dataService.getLastYearSumMoney();
        BigDecimal yearSumPercentage = null;
        if(lastYearSumMoney != 0){
            if (yearSumMoney > lastYearSumMoney) {
                yearSumPercentage = new BigDecimal((yearSumMoney.doubleValue() - lastYearSumMoney.doubleValue()) / lastYearSumMoney.doubleValue()).setScale(3,BigDecimal.ROUND_UP);
            } else {
                yearSumPercentage = new BigDecimal((lastYearSumMoney.doubleValue() - yearSumMoney.doubleValue()) / lastYearSumMoney.doubleValue()).setScale(3,BigDecimal.ROUND_UP);
            }
        }else {
            yearSumPercentage = new BigDecimal(yearSumMoney);
        }
        model.addAttribute("yearSumMoney", yearSumMoney);
        model.addAttribute("lastYearSumMoney", lastYearSumMoney);
        model.addAttribute("yearSumPercentage", yearSumPercentage);

        Long monthSumMoney = dataService.getMonthSumMoney();
        Long lstMonthSumMoney = dataService.getLstMonthSumMoney();
        BigDecimal monthSumPercentage = null;
        if(lstMonthSumMoney != 0){
        if (monthSumMoney > lstMonthSumMoney) {
            monthSumPercentage = new BigDecimal((monthSumMoney.doubleValue() - lstMonthSumMoney.doubleValue()) / lstMonthSumMoney.doubleValue()).setScale(3,BigDecimal.ROUND_UP);
        } else {
            monthSumPercentage = new BigDecimal((lstMonthSumMoney.doubleValue() - monthSumMoney.doubleValue()) / lstMonthSumMoney.doubleValue()).setScale(3,BigDecimal.ROUND_UP);
        }}else {
            monthSumPercentage = new BigDecimal(monthSumMoney);
        }
        model.addAttribute("monthSumMoney", monthSumMoney);
        model.addAttribute("lstMonthSumMoney", lstMonthSumMoney);
        model.addAttribute("monthSumPercentage", monthSumPercentage);

        Long yearPaySumMoney = dataService.getYearPaySumMoney();
        Long lastYearPaySumMoney = dataService.getLastYearPaySumMoney();
        BigDecimal yearPayPercentage = null;
        if (lastYearPaySumMoney != 0){
        if (yearPaySumMoney > lastYearPaySumMoney) {
            yearPayPercentage = new BigDecimal((yearPaySumMoney.doubleValue() - lastYearPaySumMoney.doubleValue()) / lastYearPaySumMoney.doubleValue()).setScale(3,BigDecimal.ROUND_UP);
        } else {
            yearPayPercentage = new BigDecimal((lastYearPaySumMoney.doubleValue() - yearPaySumMoney.doubleValue()) / lastYearPaySumMoney.doubleValue()).setScale(3,BigDecimal.ROUND_UP);
        }}else {
            yearPayPercentage = new BigDecimal(yearPaySumMoney);
        }
        model.addAttribute("yearPaySumMoney", yearPaySumMoney);
        model.addAttribute("lastYearPaySumMoney", lastYearPaySumMoney);
        model.addAttribute("yearPayPercentage", yearPayPercentage);

        Long monthPaySumMoney = dataService.getMonthPaySumMoney();
        Long lastMonthPaySumMoney = dataService.getLastMonthPaySumMoney();
        BigDecimal monthPayPercentage = null;
        if (lastMonthPaySumMoney != 0) {
        if (monthPaySumMoney > lastMonthPaySumMoney) {
            monthPayPercentage = new BigDecimal((monthPaySumMoney.doubleValue() - lastMonthPaySumMoney.doubleValue()) / lastMonthPaySumMoney.doubleValue()).setScale(3,BigDecimal.ROUND_UP);
        } else {
            monthPayPercentage = new BigDecimal((lastMonthPaySumMoney.doubleValue() - monthPaySumMoney.doubleValue()) / lastMonthPaySumMoney.doubleValue()).setScale(3,BigDecimal.ROUND_UP);
        }}else {
            monthPayPercentage = new BigDecimal(monthPaySumMoney);
        }
        model.addAttribute("monthPaySumMoney", monthPaySumMoney);
        model.addAttribute("lastMonthPaySumMoney", lastMonthPaySumMoney);
        model.addAttribute("monthPayPercentage", monthPayPercentage);
        return "view/console/console2";
    }

    /**
     * 订单管理
     *
     * @return
     */
    @RequestMapping(value = "/system/order")
    public String disToPageOrder() {
        return "view/information/order/order";
    }

    /**
     * 板材管理
     *
     * @return
     */
    @RequestMapping(value = "/system/board")
    public String disToPageBoard() {
        return "view/information/board/board";
    }

    /**
     * 方料管理
     *
     * @return
     */
    @RequestMapping(value = "/system/batten")
    public String disToPageBatten() {
        return "view/information/batten/batten";
    }

    /**
     * 返工信息
     *
     * @return
     */
    @RequestMapping(value = "/maintain/rework")
    public String disToPageRework() {
        return "view/maintain/rework/rework";
    }

    /**
     * 零散统计
     *
     * @return
     */
    @RequestMapping(value = "/maintain/scatter")
    public String disToPageScatter() {
        return "view/maintain/scatter/scatter";
    }

    /**
     * 库存管理
     *
     * @return
     */
    @RequestMapping(value = "/maintain/stock")
    public String disToPageStock() {
        return "view/maintain/stock/stock";
    }

    /**
     * 发货信息
     *
     * @return
     */
    @RequestMapping(value = "/system/send")
    public String disToPageSend() {
        return "view/customers/send/send";
    }

    /**
     * 客户信息
     *
     * @return
     */
    @RequestMapping(value = "/system/customer")
    public String disToPageCustomer() {
        return "view/customers/customer/customer";
    }

    /**
     * 报价管理
     *
     * @return
     */
    @RequestMapping(value = "/system/offer")
    public String disToPageOffer() {
        return "view/system/offer/offer";
    }

    /**
     * 工价管理
     *
     * @return
     */
    @RequestMapping(value = "/system/wages")
    public String disToPageWages() {
        return "view/system/wages/wages";
    }

    /**
     * 工人管理
     *
     * @return
     */
    @RequestMapping(value = "/system/worker")
    public String disToPageWorker() {
        return "view/system/worker/worker";
    }

    /**
     * 字典管理
     *
     * @return
     */
    @RequestMapping(value = "/system/dictionaries")
    public String disToPageDictionaries() {
        return "view/system/dictionaries/dictionaries";
    }

    /**
     * 个人信息
     *
     * @return
     */
    @RequestMapping(value = "/system/person")
    public String disToPagePerson() {
        return "view/user/person";
    }

    /**********************upload测试***********************/
    @RequestMapping(value = "/upload/file")
    public String disToPageUpload() {
        return "view/user/fileupload";
    }

    @RequestMapping(value = "/upload/abc")
    public String disToPageUploadFile() {
        return "view/user/fileuploads";
    }
    /**********************upload测试***********************/
}

package com.oac.project.system.customer.controller;

import com.oac.project.common.utils.PageUtil;
import com.oac.project.common.utils.SaveUtil;
import com.oac.project.system.customer.domain.Customer;
import com.oac.project.system.customer.service.CustomerService;
import com.oac.project.system.customer.vo.CustomerVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Customer API
 */
@RestController
@RequestMapping(value = "/system")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    private List<Customer> customer = null;

    /**
     * 根据Urban(二级)城市id查询用户信息
     *
     * @param cityId Urban(二级)城市id
     * @return
     */
    @RequestMapping(value = "/customer/customerByUrban")
    public List<Customer> getCustomerByUrban(@RequestParam("cityId") Long cityId) {
        customer = customerService.getCustomerByCityId(cityId);
        return customer;
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param customerId 用户id
     * @return
     */
    @RequestMapping(value = "/customer/customerById")
    public List<Customer> getCustomerById(@RequestParam("customerId") Long customerId) {
        customer = customerService.getCustomerById(customerId);
        return customer;
    }

    /**
     * 查询全部客户信息
     */
    @RequestMapping(value = "/customer/select")
    public PageUtil<CustomerVO> getAllCustomer(@RequestParam("page") Integer page,
                                               @RequestParam("limit") Integer limit,
                                               String customerNameParam,
                                               String customerPhoneParam) {
        String customerName = "";              // 客户名称条件
        if (customerNameParam == "" || customerNameParam == null) {
            customerName = null;
        } else {
            customerName = customerNameParam;
        }
        // 将orderNameParam处理 防止属性="" 查询的信息错误
        String customerPhone = "";                 // 订单名称条件
        if (customerPhoneParam == "" || customerPhoneParam == null) {
            customerPhone = null;
        } else {
            customerPhone = customerPhoneParam;
        }
        List<CustomerVO> customers = customerService.getAllCustomer(page, limit, customerName, customerPhone);
        PageUtil<CustomerVO> jsonCustomer = new PageUtil<>();
        jsonCustomer.setCode(0);
        jsonCustomer.setCount(customerService.getCount(customerName, customerPhone));
        jsonCustomer.setMsg("");
        jsonCustomer.setData(customers);
        return jsonCustomer;
    }


    /**
     * 添加客户信息
     */
    @RequestMapping(value = "/customer/save")
    public SaveUtil saveCustomerInfo(@RequestBody Customer customer) {
        // System.out.println(customer);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = null;
        try {
            result = customerService.saveCustomerInfo(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                saveUtil.setSuccess(true);
                saveUtil.setMsg("添加成功");
            } else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统异常");
            }
        }

        return saveUtil;
    }

    /**
     * 修改客户信息
     */
    @RequestMapping(value = "/customer/update", method = RequestMethod.POST)
    public SaveUtil updateCustomerInfo(@RequestBody Customer customer) {
        System.out.println(customer);
        SaveUtil saveUtil = new SaveUtil();
        Integer result = null;
        try {
            result = customerService.updateCustomerInfo(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                saveUtil.setSuccess(true);
                saveUtil.setMsg("修改成功");
            } else {
                saveUtil.setSuccess(false);
                saveUtil.setMsg("系统异常");
            }
        }
        return saveUtil;
    }

    /**
     * 删除指定id的客户信息
     *
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/customer/remove/{customerId}")
    public SaveUtil deleteCustomerInfo(@PathVariable("customerId") Long customerId) {
        System.out.println(customerId);
        SaveUtil customerUtil = new SaveUtil();
        Integer result = null;
        try {
            result = customerService.deleteCustomerInfo(customerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result == null) {
                customerUtil.setSuccess(false);
                customerUtil.setMsg("系统异常！与该客户还存在业务上的联系！");
            } else {
                customerUtil.setSuccess(true);
                customerUtil.setMsg("删除成功！");
            }
        }
        return customerUtil;
    }

    /**
     * 批量删除选中id的客户信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/customer/batchRemove/{ids}")
    public SaveUtil deleteMoreCustomerInfo(@PathVariable("ids") Long[] ids) {
        for (Long id : ids) {
            System.out.println(id);
        }
        SaveUtil customerUtil = new SaveUtil();
        Integer result = null;
        try {
            result = customerService.deleteMoreCustomerInfo(ids);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result > 0) {
                customerUtil.setSuccess(true);
                customerUtil.setMsg("删除成功！");
            } else {
                customerUtil.setSuccess(false);
                customerUtil.setMsg("系统异常！");
            }
        }
        return customerUtil;
    }
}
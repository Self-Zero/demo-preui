package com.oac.project.system.customer.controller;

import com.oac.project.system.city.domain.City;
import com.oac.project.system.city.service.CityService;
import com.oac.project.system.customer.domain.Customer;
import com.oac.project.system.customer.service.CustomerService;
import com.oac.project.system.customer.vo.CustomerVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/view/system")
public class CustomerViewController {

    @Resource
    private CityService cityService;
    @Resource
    private CustomerService customerService;

    @RequestMapping(value = "/customer/add")
    public String disToPageCustomerAdd(Model model) {
        List<City> cityByParent = cityService.getCityByParent();
        model.addAttribute("cityByParent", cityByParent);
        return "view/customers/customer/operate/add";
    }

    @RequestMapping(value = "/customer/edit")
    public String disToPageCustomerEdit(Model model, @RequestParam("customerId") Long customerId) {
        List<CustomerVO> customerById = customerService.getAllCustomer(null, null, null, null);
        for (CustomerVO customer : customerById) {
            if (customerId.equals(customer.getCustomerId())) {
                model.addAttribute("customer", customer);
            }
        }
        List<City> cityByParent = cityService.getCityByParent();
        model.addAttribute("cityByParent", cityByParent);
        return "view/customers/customer/operate/edit";
    }
}

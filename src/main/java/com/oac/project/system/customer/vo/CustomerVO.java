package com.oac.project.system.customer.vo;

import lombok.Data;

public @Data
class CustomerVO {

    /**
     * 客户标识id
     */
    private Long customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 城市i标识d
     */
    private Long addressId;
    private Long parentId;
    private String cityName;

    /**
     * 联系方式
     */
    private String customerPhone;


    private StringBuffer customerAddress;

    private Long orderNumber;

    private String created;
}

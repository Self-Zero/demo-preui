package com.oac.project.system.logistics.vo;

import lombok.Data;

public @Data
class LogisticsVO {


    /**
     * 物流id
     */
    private Long logisticsId;

    /**
     * 订单id
     */
    private Long orderId;
    private String orderName;

    /**
     * 客户id
     */
    private Long customerId;
    private String customerName;
    private StringBuffer customerAddress;
    private String customerPhone;

    /**
     * 物流公司id
     */
    private Long logisticsCompanyId;
    private String companyName;
    private Long companyPhone;

    /**
     * 物流件数
     */
    private Integer logisticsNumber;

    /**
     * 物流价格
     */
    private Long logisticsPrice;

    /**
     * 发货日期
     */
    private String created;

}

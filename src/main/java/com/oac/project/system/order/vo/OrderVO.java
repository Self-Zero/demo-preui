package com.oac.project.system.order.vo;

import lombok.Data;

@Data
public class OrderVO {

    private Long orderId;
    private String orderName;
    private String orderNumber;
    private String orderColor;
    private Long orderPrice;
    private Long orderTotal;
    private Integer orderState;
    private String remarks;
    private String reworkDate;
    private String sendDate;
    private String demandPic1;
    private String demandPic2;
    private String demandPic3;
    private String demandPic4;
    private String demandPic5;
    private String designChart;
    private String created;

    private Long customerId;
    private String customerName;
    private String phone;
    private Long addressId;
    private String cityName;
    private Long parentId;
    private Long materialId;
    private String materialName;
    private Long battenVersionId;
    private String battenVersionName;
    private Long battenBigId;
    private String battenBigName;
    private Long battenSmallId;
    private String battenSmallName;
    private Long modeId;
    private String modeName;
    private String[] imgUrl;
}

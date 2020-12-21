package com.oac.project.system.order.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author Hunter
 * @Date 2020-06-08
 */


@Entity
@Table(name = "sys_order_info")
public @Data class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1775019960357574708L;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 地址id
     */
    @Column(name = "address_id")
    private Long addressId;

    /**
     * 订单名称
     */
    @Column(name = "order_name")
    private String orderName;

    /**
     * 客户id
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 材质id
     */
    @Column(name = "material_id")
    private Long materialId;

    /**
     * 产品类型（0楼梯，1铺板，2护栏，3铺板护栏）
     */
    @Column(name = "mode_id")
    private Long modeId;

    /**
     * 订单单位（步数，米数，平方数）
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 订单颜色
     */
    @Column(name = "order_color")
    private String orderColor;

    /**
     * 柱型id
     */
    @Column(name = "batten_version_id")
    private Long battenVersionId;

    /**
     * 大柱子id
     */
    @Column(name = "batten_big_id")
    private Long battenBigId;

    /**
     * 小柱子id
     */
    @Column(name = "batten_small_id")
    private Long battenSmallId;

    /**
     * 订单定金
     */
    @Column(name = "order_price")
    private Long orderPrice;

    /**
     * 订单总金
     */
    @Column(name = "order_total")
    private Long orderTotal;

    /**
     * 订单状态(0未完成，1已完成）
     */
    @Column(name = "order_state")
    private Integer orderState;

    /**
     * 备注信息
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * 发货日期
     */
    @Column(name = "rework_date")
    private Date reworkDate;

    /**
     * 发货日期
     */
    @Column(name = "send_date")
    private Date sendDate;

    /**
     * 图片需求1
     */
    @Column(name = "demand_pic1")
    private String demandPic1;

    /**
     * 图片需求2
     */
    @Column(name = "demand_pic2")
    private String demandPic2;

    /**
     * 图片需求3
     */
    @Column(name = "demand_pic3")
    private String demandPic3;

    /**
     * 图片需求4
     */
    @Column(name = "demand_pic4")
    private String demandPic4;

    /**
     * 图片需求5
     */
    @Column(name = "demand_pic5")
    private String demandPic5;

    /**
     * 设计图
     */
    @Column(name = "design_chart")
    private String designChart;

    /**
     * 创建日期
     */
    @Column(name = "created")
    private Date created;

    /**
     * 创建人
     */
    @Column(name = "createdby")
    private Long createdby;

    /**
     * 修改日期
     */
    @Column(name = "updated")
    private Date updated;

    /**
     * 修改人
     */
    @Column(name = "updatedby")
    private Long updatedby;

}

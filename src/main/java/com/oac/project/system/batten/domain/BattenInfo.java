package com.oac.project.system.batten.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author OAC
 * @Date 2020-06-08
 */

@Entity
@Table(name = "sys_batten_info")
public @Data
class BattenInfo implements Serializable {

    private static final long serialVersionUID = 7903086935596602385L;

    /**
     * 方料信息id
     */
    @Column(name = "batten_info_id")
    private Long battenInfoId;

    /**
     * 材质id
     */
    @Column(name = "material_id")
    private Long materialId;

    /**
     * 方料类型id
     */
    @Column(name = "batten_type_id")
    private Long battenTypeId;


    /**
     * 方料数量
     */
    @Column(name = "batten_number")
    private Long battenNumber;

    /**
     * 方料单价
     */
    @Column(name = "batten_price")
    private Long battenPrice;

    /**
     * 方料总价
     */
    @Column(name = "batten_total")
    private Long battenTotal;

    /**
     * 备注
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * 创建时间
     */
    @Column(name = "created")
    private Date created;

    /**
     * 创建人
     */
    @Column(name = "createdby")
    private Long createdby;

    /**
     * 修改时间
     */
    @Column(name = "updated")
    private Date updated;

    /**
     * 修改人
     */
    @Column(name = "updatedby")
    private Long updatedby;

}

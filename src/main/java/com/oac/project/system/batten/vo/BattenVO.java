package com.oac.project.system.batten.vo;

import lombok.Data;

@Data
public class BattenVO {

    /**
     * 方料信息id
     */
    private Long battenInfoId;
    /**
     * 方料类型id
     */
    private Long battenTypeId;
    /**
     * 类型名称
     */
    private String battenName;
    /**
     * 方料数量
     */
    private Long battenNumber;
    /**
     * 方料单价
     */
    private Long battenPrice;
    /**
     * 方料总价
     */
    private Long battenTotal;
    /**
     * 方料材质
     */
    private Long materialId;
    /**
     * 材质名称
     */
    private String materialName;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 创建人
     */
    private String createdby;
    /**
     * 修改时间
     */
    private String updated;
    /**
     * 修改人
     */
    private String updatedby;

}
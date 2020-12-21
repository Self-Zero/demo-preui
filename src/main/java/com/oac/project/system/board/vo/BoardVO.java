package com.oac.project.system.board.vo;

import lombok.Data;

@Data
public class BoardVO {

    /**
     * 板材信息id
     */
    private Long boardInfoId;
    /**
     * 板材类型id
     */
    private Long boardTypeId;
    /**
     * 类型名称
     */
    private String boardName;
    /**
     * 板材数量
     */
    private Long boardNumber;
    /**
     * 板材单价
     */
    private Long boardPrice;
    /**
     * 板材总价
     */
    private Long boardTotal;
    /**
     * 板材材质
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

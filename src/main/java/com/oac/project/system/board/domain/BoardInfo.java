package com.oac.project.system.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

@Setter
@Getter
@ToString
@Entity
@Table(name = "sys_board_info")
public class BoardInfo implements Serializable {

    private static final long serialVersionUID = 5955029221176731245L;

    /**
     * 板材信息id
     */
    @Column(name = "board_info_id")
    private Long boardInfoId;

    /**
     * 材质id
     */
    @Column(name = "material_id")
    private Long materialId;

    /**
     * 板材类型id
     */
    @Column(name = "board_type_id")
    private Long boardTypeId;

    /**
     * 板材数量
     */
    @Column(name = "board_number")
    private Long boardNumber;

    /**
     * 板材单价
     */
    @Column(name = "board_price")
    private Long boardPrice;

    /**
     * 板材总价
     */
    @Column(name = "board_total")
    private Long boardTotal;

    /**
     * 备注信息
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
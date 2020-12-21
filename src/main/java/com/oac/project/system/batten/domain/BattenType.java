package com.oac.project.system.batten.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description
 * @Author OAC
 * @Date 2020-06-08
 */

@Entity
@Table(name = "sys_batten_type")
public @Data
class BattenType implements Serializable {

    private static final long serialVersionUID = 5559604608151032648L;

    /**
     * 方料标识id
     */
    @Column(name = "batten_type_id")
    private Long battenTypeId;

    /**
     * 方料名称
     */
    @Column(name = "batten_name")
    private String battenName;

    /**
     * 方料类型（0小柱，1大柱）
     */
    @Column(name = "batten_type_name")
    private Integer battenTypeName;

}

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
@Table(name = "sys_batten_version")
public @Data
class BattenVersion implements Serializable {

    private static final long serialVersionUID = 7293834263031527456L;

    /**
     * 柱型id
     */
    @Column(name = "batten_version_id")
    private Long battenVersionId;

    /**
     * 柱型名称
     */
    @Column(name = "batten_version_name")
    private String battenVersionName;

}

package com.oac.framework.shiro.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description
 * @Author Hunter
 * @Date 2020-07-07
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 7164621307224318518L;

    /**
     * 权限标识id
     */
    @Column(name = "permission_id")
    private Integer permissionId;

    /**
     * 权限名称
     */
    @Column(name = "permission_name")
    private String permissionName;

}

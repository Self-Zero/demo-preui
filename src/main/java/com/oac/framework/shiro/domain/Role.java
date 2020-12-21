package com.oac.framework.shiro.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author Hunter
 * @Date 2020-07-07
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1656652462115914022L;

    /**
     * 角色标识id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    private List<Permission> permissions;
}

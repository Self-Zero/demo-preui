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
@Table(name = "sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 6676068408978341654L;

    /**
     * 用户标识id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户账户
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 联系方式
     */
    @Column(name = "phone")
    private Long phone;

    private List<Role> roles;

}

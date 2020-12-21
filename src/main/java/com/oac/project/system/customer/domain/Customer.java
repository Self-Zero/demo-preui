package com.oac.project.system.customer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2020-06-08 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="sys_customer" )
public class Customer implements Serializable {

	private static final long serialVersionUID =  2136765092284121864L;

	/**
	 * 客户标识id
	 */
   	@Column(name = "customer_id" )
	private Long customerId;

	/**
	 * 客户名称
	 */
   	@Column(name = "customer_name" )
	private String customerName;

	/**
	 * 城市i标识d
	 */
   	@Column(name = "address_id" )
	private Long addressId;

	/**
	 * 联系方式
	 */
   	@Column(name = "phone" )
	private String phone;

    /**
     * 创建时间
     */
    @Column(name = "created" )
   	private Date  created;

}

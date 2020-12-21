package com.oac.project.system.logistics.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2020-07-01 
 */

@Entity
@Table ( name ="sys_logistics" )
public @Data
class Logistics implements Serializable {

	private static final long serialVersionUID =  4861990650921106843L;

	/**
	 * 物流id
	 */
   	@Column(name = "logistics_id" )
	private Long logisticsId;

	/**
	 * 订单id
	 */
   	@Column(name = "order_id" )
	private Long orderId;

	/**
	 * 客户id
	 */
   	@Column(name = "customer_id" )
	private Long customerId;

	/**
	 * 物流公司id
	 */
   	@Column(name = "logistics_company_id" )
	private Long logisticsCompanyId;

	/**
	 * 物流件数
	 */
   	@Column(name = "logistics_number" )
	private Integer logisticsNumber;

	/**
	 * 物流价格
	 */
   	@Column(name = "logistics_price" )
	private Long logisticsPrice;

	/**
	 * 发货日期
	 */
   	@Column(name = "created" )
	private Date created;

}

package com.oac.project.system.distribution.domain;

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
 * @Author  OAC
 * @Date 2020-10-16 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="sys_order_distribution" )
public class OrderDistribution implements Serializable {

	private static final long serialVersionUID =  3402305001377460560L;

	/**
	 * 订单id
	 */
   	@Column(name = "order_id" )
	private Long orderId;

	/**
	 * 木工
	 */
   	@Column(name = "carpentry_id" )
	private Long carpentryId;

	/**
	 * 面漆工
	 */
   	@Column(name = "painter_id" )
	private Long painterId;

	/**
	 * 底漆工
	 */
   	@Column(name = "primer_id" )
	private Long primerId;

	/**
	 * 创建时间
	 */
	@Column(name = "created" )
   	private Date created;
}

package com.oac.project.system.rework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  OAC
 * @Date 2020-09-22 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="sys_rework" )
public class Rework implements Serializable {

	private static final long serialVersionUID =  2146707686636592299L;

   	@Column(name = "rework_id" )
	private Long reworkId;

   	@Column(name = "order_id" )
	private Long orderId;

   	@Column(name = "order_name" )
	private String orderName;

}

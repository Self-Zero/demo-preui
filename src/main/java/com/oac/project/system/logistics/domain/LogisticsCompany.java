package com.oac.project.system.logistics.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2020-07-01 
 */

@Entity
@Table ( name ="sys_logistics_company" )
public @Data
class LogisticsCompany implements Serializable {

	private static final long serialVersionUID =  1137813691159815853L;

	/**
	 * 物流公司id
	 */
   	@Column(name = "company_id" )
	private Long companyId;

	/**
	 * 物流公司名称
	 */
   	@Column(name = "company_name" )
	private String companyName;

	/**
	 * 物流公司电话
	 */
   	@Column(name = "company_phone" )
	private Long companyPhone;

}

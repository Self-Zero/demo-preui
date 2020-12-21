package com.oac.project.system.mode.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
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
@Table ( name ="sys_mode" )
public class Mode implements Serializable {

	private static final long serialVersionUID =  4268322250021463378L;

	/**
	 * 产品类型id
	 */
   	@Column(name = "mode_id" )
	private Long modeId;

	/**
	 * 产品类型名称
	 */
   	@Column(name = "mode_name" )
	private String modeName;

}

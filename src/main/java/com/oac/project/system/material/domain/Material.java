package com.oac.project.system.material.domain;

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
@Table ( name ="sys_material" )
public class Material implements Serializable {

	private static final long serialVersionUID =  4710522451300170252L;

	/**
	 * 材质标识id
	 */
   	@Column(name = "material_id" )
	private Long materialId;

	/**
	 * 材质名称
	 */
   	@Column(name = "material_name" )
	private String materialName;

}

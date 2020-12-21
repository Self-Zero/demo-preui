package com.oac.project.system.city.domain;

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
@Table ( name ="sys_city" )
public class City implements Serializable {

	private static final long serialVersionUID =  5651725626933032777L;

	/**
	 * 城市id
	 */
   	@Column(name = "city_id" )
	private Long cityId;

	/**
	 * 城市名称
	 */
   	@Column(name = "city_name" )
	private String cityName;

	/**
	 * 省份id
	 */
   	@Column(name = "parent_id" )
	private Long parentId;

}

package com.oac.project.system.worker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * @Description  
 * @Author  OAC
 * @Date 2020-09-21 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="sys_worker" )
public class Worker  implements Serializable {

	private static final long serialVersionUID =  1859717274915195767L;

	/**
	 * 工人id
	 */
   	@Column(name = "worker_id" )
	private Long workerId;

	/**
	 * 工人名字
	 */
   	@Column(name = "worker_name" )
	private String workerName;

	/**
	 * 入职日期
	 */
   	@Column(name = "entry_date" )
	private Date entryDate;

	/**
	 * 离职日期
	 */
   	@Column(name = "quit_date" )
	private Date quitDate;

	/**
	 * 工种(0-小工 1-木工 2-底漆工 3-面漆工)
	 */
   	@Column(name = "worker_type" )
	private Integer workerType;

	/**
	 * 联系方式
	 */
   	@Column(name = "worker_phone" )
	private Long workerPhone;

}

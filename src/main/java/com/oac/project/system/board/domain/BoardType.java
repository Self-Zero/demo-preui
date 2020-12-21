package com.oac.project.system.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description
 * @Author Hunter
 * @Date 2020-06-08
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "sys_board_type")
public class BoardType implements Serializable {

    private static final long serialVersionUID = 748067858780713726L;

    /**
     * 板材标识id
     */
    @Column(name = "board_type_id")
    private Long boardTypeId;

    /**
     * 板材名称
     */
    @Column(name = "board_name")
    private String boardName;

}

package com.hope.mybatis.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 通用实体类
 * @program:hope-plus
 * @author:aodeng
 * @create:2018-10-15 13:48
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class CommonEntity implements Serializable{

    private static final long serialVersionUID = 767754740035356753L;
    /**编号，主键，资源表**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**创建时间**/
    @Column(name = "create_time")
    private Date createtime;
    /**修改时间**/
    @Column(name = "update_time")
    private Date updatetime;

}

package com.monomer.alexander.common;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description:
 * @Author: XiaoYu
 * @Date: 2020-06-07 16:26
 * @Version: 1.0
 **/
@Entity
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "datetime(3) COMMENT'创建时间'")
    private Date createTime;

    @Column(columnDefinition = "datetime(3) COMMENT'更新时间'")
    private Date updateTime;
}

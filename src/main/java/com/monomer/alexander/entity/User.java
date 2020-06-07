package com.monomer.alexander.entity;

import com.monomer.alexander.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Description:
 * @Author: XiaoYu
 * @Date: 2020-06-07 15:40
 * @Version: 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class User extends BaseEntity {

    @Column(columnDefinition = "varchar(255) COMMENT'名称'")
    private String name;
}

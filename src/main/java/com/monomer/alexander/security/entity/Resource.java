package com.monomer.alexander.security.entity;

import com.monomer.alexander.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Description:
 * @Author: XiaoYu
 * @Date: 2020-06-07 16:36
 * @Version: 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Resource extends BaseEntity {

    @Column(columnDefinition = "varchar(255) COMMENT'资源名'")
    private String resourceName;

}

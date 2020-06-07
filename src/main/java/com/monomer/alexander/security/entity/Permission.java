package com.monomer.alexander.security.entity;

import com.monomer.alexander.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: XiaoYu
 * @Date: 2020-06-07 16:36
 * @Version: 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class Permission extends BaseEntity {

    @Column(columnDefinition = "varchar(255) COMMENT'权限链接'")
    private String url;
}

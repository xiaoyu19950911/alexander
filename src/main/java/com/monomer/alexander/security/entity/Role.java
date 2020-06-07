package com.monomer.alexander.security.entity;

import com.monomer.alexander.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description:
 * @Author: XiaoYu
 * @Date: 2020-06-07 16:24
 * @Version: 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class Role extends BaseEntity {

    @Column(columnDefinition = "varchar(255) COMMENT'角色名'")
    private String roleName;

}

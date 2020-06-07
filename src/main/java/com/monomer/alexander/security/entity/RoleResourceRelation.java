package com.monomer.alexander.security.entity;

import com.monomer.alexander.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Description:
 * @Author: XiaoYu
 * @Date: 2020-06-07 16:49
 * @Version: 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class RoleResourceRelation extends BaseEntity {

    @Column(columnDefinition = "int(11) COMMENT'角色id'")
    private Integer roleId;

    @Column(columnDefinition = "int(11) COMMENT'资源id'")
    private Integer resourceId;
}

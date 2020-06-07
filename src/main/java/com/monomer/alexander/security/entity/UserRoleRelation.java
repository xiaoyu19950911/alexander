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
@Data
@Entity
public class UserRoleRelation extends BaseEntity {

    @Column(columnDefinition = "int(11) COMMENT'用户id'")
    private Integer userId;

    @Column(columnDefinition = "int(11) COMMENT'角色id'")
    private Integer roleId;
}

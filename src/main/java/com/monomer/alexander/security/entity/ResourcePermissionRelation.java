package com.monomer.alexander.security.entity;

import com.monomer.alexander.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Description:
 * @Author: XiaoYu
 * @Date: 2020-06-07 16:48
 * @Version: 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ResourcePermissionRelation extends BaseEntity {

    @Column(columnDefinition = "int(11) COMMENT'资源id'")
    private Integer resourceId;

    @Column(columnDefinition = "int(11) COMMENT'权限id'")
    private Integer permissionId;
}

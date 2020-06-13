package com.monomer.alexander.security.entity;

import com.monomer.alexander.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @Description:
 * @Author: XiaoYu
 * @Date: 2020-06-07 15:46
 * @Version: 1.0
 **/

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
public class Auths extends BaseEntity {

    @Column(columnDefinition = "varchar(255) COMMENT'登陆标识'")
    private String identifier;

    @Column(columnDefinition = "varchar(255) COMMENT'密码凭证'")
    private String credential;

    @Column(columnDefinition = "int(11) COMMENT'账号状态'")
    private Integer status;

    @Column(columnDefinition = "int(11) COMMENT'用户id'")
    private Integer userId;

    @Column(columnDefinition = "varchar(255) COMMENT'token'")
    private String token;
}

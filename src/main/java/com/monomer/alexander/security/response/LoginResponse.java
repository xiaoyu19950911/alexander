package com.monomer.alexander.security.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @program: rry
 * @description:
 * @author: XiaoYu
 * @create: 2018-04-09 16:57
 **/
@ApiModel
@Data
@Builder
public class LoginResponse {

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("token过期时刷新token")
    private String refreshToken;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户角色")
    private List<String> roleNameList;

}

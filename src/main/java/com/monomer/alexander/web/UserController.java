package com.monomer.alexander.web;

import com.monomer.alexander.common.Result;
import com.monomer.alexander.common.ResultUtils;
import com.monomer.alexander.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: XiaoYu
 * @Date: 2020-06-07 18:49
 * @Version: 1.0
 **/
@Controller
@Api(value = "user", tags = "用户相关接口")
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/getUser")
    @ApiOperation("获取用户信息")
    @ResponseBody
    public Result<Object> getUser() {
        User user = new User();
        user.setName("肖宇");
        return ResultUtils.success(user);
    }
}

package com.monomer.alexander.security.auth;

import com.monomer.alexander.common.Result;
import com.monomer.alexander.common.ResultUtils;
import com.monomer.alexander.security.jwt.JwtAuthenticationRequest;
import com.monomer.alexander.security.request.RegistRequest;
import com.monomer.alexander.security.response.LoginResponse;
import com.monomer.alexander.security.response.TokenGetResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Api(value = "auth", tags = "授权相关接口")
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @GetMapping(value = "/test")
    @ApiOperation("test")
    @ResponseBody
    public Result<Object> createAuthenticationToken() throws Exception {
        return ResultUtils.success("test");
    }

    @PostMapping(value = "/getToken")
    @ApiOperation("登陆并获取token")
    @ResponseBody
    public Result<LoginResponse> createAuthenticationToken(@Valid @RequestBody JwtAuthenticationRequest authenticationRequest, BindingResult bindingResult) throws Exception {
        return authService.login(authenticationRequest);
    }

    @ApiOperation("退出并更新token")
    @ResponseBody
    @RequestMapping(value = "${jwt.route.authentication.logout}", method = RequestMethod.POST)
    @ApiIgnore
    public Result<Object> deleteAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        authService.refresh(token);
        return ResultUtils.success();
    }

    @ApiOperation("刷新token")
    @ResponseBody
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public Result<TokenGetResponse> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        return authService.refresh(token);
    }

    @ApiOperation("超级管理员注册账号(web_admin、app)")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ROOT')")
    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public Result<Object> register(@Valid @RequestBody RegistRequest request, BindingResult bindingResult) throws Exception {
        return authService.register(request);
    }





}

package com.monomer.alexander.security.auth;


import com.monomer.alexander.common.Result;
import com.monomer.alexander.security.entity.Auths;
import com.monomer.alexander.security.entity.Permission;
import com.monomer.alexander.security.jwt.JwtAuthenticationRequest;
import com.monomer.alexander.security.request.RegistRequest;
import com.monomer.alexander.security.response.LoginResponse;
import com.monomer.alexander.security.response.TokenGetResponse;

import java.util.Set;

public interface AuthService {
    Result<Object> register(RegistRequest request) throws Exception;

    Result<LoginResponse> login(JwtAuthenticationRequest authenticationRequest) throws Exception;

    Result<TokenGetResponse> refresh(String oldToken);


    Auths findFirstByUsername(String username);

    Set<Permission> getPermissionSet(Integer userId);

    Set<String> getPermissionUrlSet(Integer userId);
}

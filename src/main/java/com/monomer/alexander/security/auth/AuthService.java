package com.monomer.alexander.security.auth;


import com.monomer.alexander.common.Result;
import com.monomer.alexander.security.jwt.JwtAuthenticationRequest;
import com.monomer.alexander.security.request.RegistRequest;
import com.monomer.alexander.security.response.LoginResponse;
import com.monomer.alexander.security.response.TokenGetResponse;

public interface AuthService {
    Result<Object> register(RegistRequest request) throws Exception;

    Result<LoginResponse> login(JwtAuthenticationRequest authenticationRequest) throws Exception;

    Result<TokenGetResponse> refresh(String oldToken);


}

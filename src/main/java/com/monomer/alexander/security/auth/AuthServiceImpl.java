package com.monomer.alexander.security.auth;

import com.monomer.alexander.common.Result;
import com.monomer.alexander.security.jwt.JwtAuthenticationRequest;
import com.monomer.alexander.security.request.RegistRequest;
import com.monomer.alexander.security.response.LoginResponse;
import com.monomer.alexander.security.response.TokenGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    public Result<Object> register(RegistRequest request) throws Exception {
        return null;
    }

    @Override
    public Result<LoginResponse> login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        return null;
    }

    @Override
    public Result<TokenGetResponse> refresh(String oldToken) {
        return null;
    }
}

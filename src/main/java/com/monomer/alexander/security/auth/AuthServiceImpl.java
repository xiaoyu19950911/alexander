package com.monomer.alexander.security.auth;

import com.google.common.collect.Sets;
import com.monomer.alexander.common.Result;
import com.monomer.alexander.common.ResultUtils;
import com.monomer.alexander.enums.ProgramEnums;
import com.monomer.alexander.exception.AccountException;
import com.monomer.alexander.exception.CommunalException;
import com.monomer.alexander.security.entity.*;
import com.monomer.alexander.security.jwt.JwtAuthenticationRequest;
import com.monomer.alexander.security.jwt.JwtTokenUtil;
import com.monomer.alexander.security.jwt.JwtUser;
import com.monomer.alexander.security.repository.*;
import com.monomer.alexander.security.request.RegistRequest;
import com.monomer.alexander.security.response.LoginResponse;
import com.monomer.alexander.security.response.TokenGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthsRepository authsRepository;

    @Autowired
    UserRoleRelationRepository userRoleRelationRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleResourceRelationRepository roleResourceRelationRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ResourcePermissionRelationRepository resourcePermissionRelationRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public Result<Object> register(RegistRequest request) throws Exception {
        return null;
    }

    @Override
    public Result<LoginResponse> login(JwtAuthenticationRequest request) throws Exception {
        LoginResponse result = LoginResponse.builder().build();
        UsernamePasswordAuthenticationToken upToken;
        Auths auths = authsRepository.findFirstByIdentifier(request.getUsername());
        if (auths == null)
            throw new CommunalException(-1, "用户名不存在！");
        upToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);//保存当前用户信息
        // Reload password post-security so we can generate token
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(request.getUsername());
        String token = JwtTokenUtil.generateToken(jwtUser);
        String refreshToken = JwtTokenUtil.generateRefreshToken(jwtUser);
        Integer userId = auths.getUserId();
        if (ProgramEnums.STATUS_DISABLED.getCode().equals(auths.getStatus()))
            throw new AccountException(-1, "该账户已停用！");
        auths.setToken(token);

        List<UserRoleRelation> userRoleRelationList = userRoleRelationRepository.findAllByUserId(userId);
        List<String> roleNameList = userRoleRelationList.stream().map(userRoleRelation -> roleRepository.getOne(userRoleRelation.getRoleId()).getRoleName()).collect(Collectors.toList());

        result.setRoleNameList(roleNameList);
        result.setToken(token);
        result.setRefreshToken(refreshToken);
        result.setUserId(userId);
        return ResultUtils.success(result);
    }

    @Override
    public Result<TokenGetResponse> refresh(String oldToken) {
        return null;
    }

    @Override
    public Auths findFirstByUsername(String username) {
        return null;
    }

    @Override
    public Set<Permission> getPermissionSet(Integer userId) {
        List<Role> roleList = userRoleRelationRepository.findAllByUserId(userId).stream().map(userRoleRelation -> roleRepository.findById(userRoleRelation.getRoleId()).get()).collect(Collectors.toList());
        Set<Resource> resourceSet = Sets.newHashSet();
        for (Role role : roleList){
            List<RoleResourceRelation> roleResourceRelationList = roleResourceRelationRepository.findAllByRoleId(role.getId());
            for (RoleResourceRelation roleResourceRelation : roleResourceRelationList){
                Resource resource = resourceRepository.findById(roleResourceRelation.getResourceId()).get();
                resourceSet.add(resource);
            }
        }

        Set<Permission> permissionSet = Sets.newHashSet();
        for (Resource resource : resourceSet){
            List<ResourcePermissionRelation> resourcePermissionRelationList = resourcePermissionRelationRepository.findAllByResourceId(resource.getId());
            for (ResourcePermissionRelation resourcePermissionRelation : resourcePermissionRelationList){
                Permission permission = permissionRepository.findById(resourcePermissionRelation.getResourceId()).get();
                permissionSet.add(permission);
            }
        }
        return permissionSet;
    }

    @Override
    public Set<String> getPermissionUrlSet(Integer userId) {
        List<Role> roleList = userRoleRelationRepository.findAllByUserId(userId).stream().map(userRoleRelation -> roleRepository.findById(userRoleRelation.getRoleId()).get()).collect(Collectors.toList());
        Set<Resource> resourceSet = Sets.newHashSet();
        for (Role role : roleList){
            List<RoleResourceRelation> roleResourceRelationList = roleResourceRelationRepository.findAllByRoleId(role.getId());
            for (RoleResourceRelation roleResourceRelation : roleResourceRelationList){
                Resource resource = resourceRepository.findById(roleResourceRelation.getResourceId()).get();
                resourceSet.add(resource);
            }
        }

        Set<String> permissionUrlSet = Sets.newHashSet();
        for (Resource resource : resourceSet){
            List<ResourcePermissionRelation> resourcePermissionRelationList = resourcePermissionRelationRepository.findAllByResourceId(resource.getId());
            for (ResourcePermissionRelation resourcePermissionRelation : resourcePermissionRelationList){
                Permission permission = permissionRepository.findById(resourcePermissionRelation.getResourceId()).get();
                permissionUrlSet.add(permission.getUrl());
            }
        }
        return permissionUrlSet;
    }
}

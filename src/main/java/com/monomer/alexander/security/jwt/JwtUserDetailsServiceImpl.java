package com.monomer.alexander.security.jwt;


import com.monomer.alexander.security.entity.Auths;
import com.monomer.alexander.security.entity.Role;
import com.monomer.alexander.security.entity.UserRoleRelation;
import com.monomer.alexander.security.repository.AuthsRepository;
import com.monomer.alexander.security.repository.RoleRepository;
import com.monomer.alexander.security.repository.UserRoleRelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AuthsRepository authsRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRelationRepository userRoleRelationRepository;



    @Override
    public JwtUser loadUserByUsername(String identifier) throws UsernameNotFoundException {
        List<Auths> authsList = authsRepository.findAllByIdentifierAndIdentityType(identifier,"1");
        if (authsList == null || authsList.size() == 0)
            throw new UsernameNotFoundException(String.format("No user found with identifier '%s'.", identifier));
        if (authsList.size()>1)
            throw new SecurityException("该用户异常，请联系管理员");
        Auths auths = authsList.get(0);
        log.debug("当前用户id为：{}",auths.getUserId());
        List<Role> roleList = userRoleRelationRepository.findAllByUserId(auths.getUserId()).stream().map(userRoleRelation -> roleRepository.getOne(userRoleRelation.getRoleId())).collect(Collectors.toList());
        return JwtUserFactory.create(auths,roleList);
    }
}

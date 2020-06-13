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

import java.util.ArrayList;
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
        Auths auths = authsRepository.findFirstByIdentifier(identifier);
        if (auths == null)
            throw new UsernameNotFoundException(String.format("No user found with identifier '%s'.", identifier));
        log.debug("当前用户id为：{}",auths.getUserId());

        List<Role> roleList = userRoleRelationRepository.findAllByUserId(auths.getUserId()).stream().map(userRoleRelation -> roleRepository.findById(userRoleRelation.getRoleId()).get()).collect(Collectors.toList());
        return JwtUserFactory.create(auths,roleList);
    }
}

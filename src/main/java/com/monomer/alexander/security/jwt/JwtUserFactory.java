package com.monomer.alexander.security.jwt;

import com.monomer.alexander.security.entity.Auths;
import com.monomer.alexander.security.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtUserFactory {

    public JwtUserFactory() {

    }

    public static JwtUser create(Auths user, List<Role> roleList) {
        return new JwtUser(
                user.getId().toString(),
                user.getIdentifier(),
                user.getCredential(),
                mapToGrantedAuthorities(roleList)
        );
    }

    /*private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }*/

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
        authorities.forEach(a-> log.debug("当前用户角色为"+a.getRoleName()));
        return authorities.stream()
                .map(e->new SimpleGrantedAuthority(e.getRoleName()))
                .collect(Collectors.toList());
    }

}

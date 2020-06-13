package com.monomer.alexander.security.jwt;

import com.monomer.alexander.security.auth.AuthService;
import com.monomer.alexander.security.entity.Permission;
import com.monomer.alexander.security.entity.Resource;
import com.monomer.alexander.security.entity.Role;
import com.monomer.alexander.security.repository.RoleRepository;
import com.monomer.alexander.security.repository.RoleResourceRelationRepository;
import com.monomer.alexander.security.repository.UserRoleRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserRoleRelationRepository userRoleRelationRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleResourceRelationRepository roleResourceRelationRepository;

    @Autowired
    AuthService authService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /*@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UsernamePasswordAuthenticationToken authentication=tokenAuthenticationService.getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.setStatus(HttpServletResponse.SC_OK);
        filterChain.doFilter(request, response);
    }*/


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        /**
         * 第二种校验
         */
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            request.setAttribute("exception", "invalidToken");
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        //authentication = null;
        if (authentication == null)
            request.setAttribute("exception", "invalidToken");
        if (validPermissions(request)) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private boolean validPermissions(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String authToken = authHeader.substring(tokenHead.length());
        String url = request.getRequestURI();
        Integer userId = JwtTokenUtil.getUserIdFromToken(authToken);
        Set<String> permissionUrlSet = authService.getPermissionUrlSet(userId);
        if (!permissionUrlSet.contains(url) && !permissionUrlSet.contains("*")) {
            request.setAttribute("exception", "noPermissions");
            return false;
        }
        return true;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.isEmpty()) {
            request.setAttribute("exception", "invalidToken");
            //throw new CommunalException(-1,"Token为空");
        }
        String authToken = authHeader.substring(tokenHead.length());
        // parse the token.
        String username = JwtTokenUtil.getUsernameFromToken(authToken);
        String tokenType = JwtTokenUtil.getTokenTypeFromToken(authToken);
        if (username != null && tokenType.equals("token")) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }


        return null;
    }
}

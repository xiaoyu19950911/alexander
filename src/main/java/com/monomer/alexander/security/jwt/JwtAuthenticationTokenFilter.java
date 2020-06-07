package com.monomer.alexander.security.jwt;

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

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    JwtTokenUtil jwtUtils;

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
            request.setAttribute("exception","invalidToken");
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication==null)
            request.setAttribute("exception","invalidToken");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.isEmpty()) {
            request.setAttribute("exception","invalidToken");
            //throw new CommunalException(-1,"Token为空");
        }
        String authToken = authHeader.substring(tokenHead.length());
        // parse the token.
        String username = jwtUtils.getUsernameFromToken(authToken);
        String tokenType=jwtUtils.getTokenTypeFromToken(authToken);
            if (username != null&&tokenType.equals("token")) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }


        return null;
    }
}

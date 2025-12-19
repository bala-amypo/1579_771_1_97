package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter 
{

    private final JwtUtil jwtUtil;

    // Example secret and expiration; in production read from properties
    public JwtFilter() 
    {
        this.jwtUtil = new JwtUtil("super-secret-key-please-change", 3600_000L);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) 
    {
        return new AntPathRequestMatcher("/auth/**").matches(request)
                || new AntPathRequestMatcher("/swagger-ui/**").matches(request)
                || new AntPathRequestMatcher("/v3/api-docs/**").matches(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException 
    {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) 
        {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) 
            {
                Map<String, Object> claims = jwtUtil.parseToken(token);
                String email = String.valueOf(claims.getOrDefault("sub", ""));
                String role = String.valueOf(claims.getOrDefault("role", "STAFF"));
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null, List.of(authority));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}

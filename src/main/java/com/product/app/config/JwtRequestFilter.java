package com.product.app.config;

import com.product.app.service.JwtUserServiceImpl;
import com.product.app.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
   @Autowired
   private JwtTokenUtil jwtTokenUtil;
   @Autowired
   private JwtUserServiceImpl jwtUserServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get the header with the name Authorization
        String header = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;

        //check if header starts with bearer
        if (header != null && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7);
            try {
                //get the username from the token
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("unable to get name");
            } catch (ExpiredJwtException e) {
                System.out.println("Token has expired");
            }
        } else {
            logger.warn("Invalid header");
        }
        // if the username is not null, check if the username is in the database
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = jwtUserServiceImpl.loadUserByUsername(username);
            // validate the token
            //if valid configure the security manually
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
            filterChain.doFilter(request, response);

    }
}

package com.HopeLunchSystem.HLS.config;

import com.HopeLunchSystem.HLS.constants.AppConstants;
import com.HopeLunchSystem.HLS.exceptions.ErrorsException;
import com.HopeLunchSystem.HLS.exceptions.ResponseMessage;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.HopeLunchSystem.HLS.constants.AppConstants.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

//    public JwtAuthenticationFilter(JWTService jwtService, UserDetailsService userDetailsService) {
//        this.jwtService = jwtService;
//        this.userDetailsService = userDetailsService;
//    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/account/login/user") ||
                request.getServletPath().contains("/api/v1/account/login/admin")
                ) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        final String jwt;
        final String username;

        // * Check if the request has the Authorization header with a Bearer token.
        // ? If not, proceed with the filter chain.
        if(authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // * Extract the JWT token from the Authorization header.
        jwt = authHeader.substring(BEARER_PREFIX_LENGTH);
        username = jwtService.extractUsername(jwt);

        // * Check if there is a userEmail in the token and check if the user is authenticated or not
        // ? When SecurityContextHolder.getContext().getAuthentication() == null means that user is not authenticated
        // ? If not, you will have to go back to UserDetailService to find out if that user is in the database or not
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            // * Load user details from the UserDetailsService using the userEmail.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // * Validate the token and authenticate the user if the token is valid.
            // ? If the token is not valid, proceed with the filter chain.
            if(jwtService.isTokenValid(jwt, userDetails)){
                // * Create an authentication token and set it in the SecurityContextHolder.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                throw new ErrorsException(ERROR_TOKEN_INVALID, HTTP_FORBIDDEN);
            }

        }
        // * Proceed with the filter chain.
        filterChain.doFilter(request,response);
    }
}

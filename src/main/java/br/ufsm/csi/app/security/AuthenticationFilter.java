package br.ufsm.csi.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

public class AuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private UserDetailsServiceImplements userDetailsSImplements;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {

    String url = request.getRequestURI();

    try {
      if (!url.contains("login")) {

        String token = request.getHeader("Authorization");
        String userName = new JWTUtil().getUserNameToken(token);
        System.out.println(userName);
        System.out.println("Token expirado?" + new JWTUtil().isTokenExpired(token));

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails user = this.userDetailsSImplements.loadUserByUsername(userName);
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(token, userName);

          if (!new JWTUtil().isTokenExpired(token)) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user, null,
                user.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            ;
          }
        }
      }

    } catch (ExpiredJwtException e) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expirado");
    }

    filterChain.doFilter(request, response);

  }

}

package br.ufsm.csi.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.ufsm.csi.app.service.AuthenticationService;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private final TokenServiceJWT tokenServiceJWT;
  private final AuthenticationService authenticationService;

  public AuthenticationFilter(TokenServiceJWT tokenJWT, AuthenticationService authentication) {
    this.tokenServiceJWT = tokenJWT;
    this.authenticationService = authentication;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
    System.out.println("Filtro para autenticação e autorização");

    String tokenJWT = recuperarToken(request);
    System.out.println("tokenJWT:" + tokenJWT);

    if (tokenJWT != null) {
      String subject = this.tokenServiceJWT.getSubject(tokenJWT);
      System.out.println("Login da req. " + subject);

      UserDetails userDetails = this.authenticationService.loadUserByUsername(subject);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
          null, userDetails.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    filterChain.doFilter(request, response);
  }

  private String recuperarToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token != null) {
      return token.replace("Bearer", "").trim();
    }
    return null;
  }
}

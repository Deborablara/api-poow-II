package br.ufsm.csi.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity {

  private final AuthenticationFilter authenticationFilter;

  public WebSecurity(AuthenticationFilter filter) {
    this.authenticationFilter = filter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeRequests(authorize -> {
          authorize
              .antMatchers(HttpMethod.GET, "/**").permitAll()
              .antMatchers(HttpMethod.POST,
                  "/clients/new",
                  "/products/new",
                  "/shipping-company/new",
                  "/vehicle/new")
              .hasAuthority("ADMIN_EMPLOYEE")
              .antMatchers(HttpMethod.PUT,
                  "/clients/update/{id}",
                  "/products/update/{id}",
                  "/shipping-company/update/{id}",
                  "/vehicle/update/{id}")
              .hasAuthority("ADMIN_EMPLOYEE")
              .antMatchers(HttpMethod.POST, "/request/new").hasAnyAuthority("ADMIN_EMPLOYEE")
              .antMatchers(HttpMethod.PUT, "/request/{id}").hasAnyAuthority("ADMIN_EMPLOYEE", "EMPLOYEE")
              .anyRequest().authenticated();
        })
        .addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

package br.ufsm.csi.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.app.model.UserModel;
import br.ufsm.csi.app.repository.UserRepository;
import br.ufsm.csi.app.security.TokenServiceJWT;

@RestController
public class LoginController {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  public LoginController(AuthenticationManager authenticationManager, UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
  }

  @PostMapping("/login")
  public ResponseEntity<Object> auth(@RequestBody UserModel user) {
    try {
      final Authentication authenticate = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

      if (authenticate.isAuthenticated()) {
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        Optional<UserModel> userAuthenticated = userRepository.findByUsername(user.getUsername());
        String token = new TokenServiceJWT().gerarToken(userAuthenticated);
        userAuthenticated.get().setToken(token);

        return new ResponseEntity<>(userAuthenticated, HttpStatus.OK);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }

    return ResponseEntity.badRequest().body("Usuário ou senha incorretos");
  }
}

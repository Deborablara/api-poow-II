package br.ufsm.csi.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.app.model.UserModel;
import br.ufsm.csi.app.repository.UserRepository;
import br.ufsm.csi.app.security.JWTUtil;

@RestController
public class LoginController {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/login")
  public ResponseEntity<Object> auth(@RequestBody UserModel user) {
    try {
      final Authentication authenticate = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

      if (authenticate.isAuthenticated()) {
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        Optional<UserModel> userAuthenticated = userRepository.findByUsername(user.getUsername());
        String token = new JWTUtil().createToken(userAuthenticated);
        userAuthenticated.get().setToken(token);

        return new ResponseEntity<>(userAuthenticated, HttpStatus.OK);
      }

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Usuário ou senha incorretos", HttpStatus.FORBIDDEN);
    }

    return null;
  }

}

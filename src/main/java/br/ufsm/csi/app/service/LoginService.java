package br.ufsm.csi.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import br.ufsm.csi.app.repository.UserRepository;

public class LoginService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  @Autowired
  public LoginService(UserRepository userRepository, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
  }

}

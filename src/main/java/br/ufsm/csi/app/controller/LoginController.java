package br.ufsm.csi.app.controller;

import br.ufsm.csi.app.DTOS.UserLogin;
import br.ufsm.csi.app.security.JwtUtil;
import net.bytebuddy.implementation.bytecode.Throw;
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

@RestController
public class LoginController {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  public LoginController(AuthenticationManager authenticationManager, UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
  }

  @PostMapping("/login")
  public ResponseEntity<Object> auth(@RequestBody UserLogin user) {
    try {
      System.out.println(user.getUsername());
      final Authentication authenticate = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

      if (authenticate.isAuthenticated()) {
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String username = authenticate.getName();
        Optional<UserModel> userModel = this.userRepository.findByUsername(username);
        if(userModel.isPresent()){

          String token = new JwtUtil().createToken(userModel.get());
          userModel.get().setToken(token);
          return new ResponseEntity<>(userModel, HttpStatus.OK);
        }else{
          throw new Exception("deu ruim");
        }



      }else{
        System.out.println("vai tomar no cu");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body("Usuário ou senha incorretos");
    }
    return ResponseEntity.ok().body("kevinho");
  }
}

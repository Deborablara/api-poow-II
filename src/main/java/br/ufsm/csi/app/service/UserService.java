package br.ufsm.csi.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufsm.csi.app.model.UserModel;
import br.ufsm.csi.app.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public ResponseEntity<UserModel> getUser(Long id) {

    try {
      UserModel user = userRepository.getReferenceById(id);
      return ResponseEntity.ok(user);

    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  public ResponseEntity<?> getUsers() {
    try {
      List<UserModel> users = userRepository.findAll();
      return ResponseEntity.ok(users);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }

  }
}

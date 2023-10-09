package br.ufsm.csi.app.service;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.forms.UserForm;
import br.ufsm.csi.app.model.RoleModel;
import br.ufsm.csi.app.model.UserModel;
import br.ufsm.csi.app.repository.RoleRepository;
import br.ufsm.csi.app.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

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

  @Transactional
  public ResponseEntity<?> newUser(UserForm userForm, UriComponentsBuilder uriComponentsBuilder) {

    RoleModel role = roleRepository.findById(userForm.getRole_id()).orElse(null);
    System.out.println(role.getRoleName());

    UserModel newUser = new UserModel();
    newUser.setUsername(userForm.getName());
    newUser.setPassword(passwordEncoder.encode(userForm.getPassword()));
    newUser.addRole(role);

    try {
      UserModel savedUser = userRepository.save(newUser);
      URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(savedUser.getUserId()).toUri();

      return ResponseEntity.created(uri).body(savedUser);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Falha ao criar novo usuário");
    }
  }
}

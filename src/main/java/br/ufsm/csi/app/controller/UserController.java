package br.ufsm.csi.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.forms.UserForm;
import br.ufsm.csi.app.model.UserModel;
import br.ufsm.csi.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/new")
  public ResponseEntity<?> createUser(@RequestBody UserForm values,
      UriComponentsBuilder uriComponentsBuilder) {
    return userService.newUser(values, uriComponentsBuilder);
  }

  @GetMapping()
  public ResponseEntity<?> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserModel> client(@PathVariable Long id) {
    return userService.getUser(id);
  }

}

package br.ufsm.csi.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.forms.RequestForm;
import br.ufsm.csi.app.forms.StatusForm;
import br.ufsm.csi.app.model.Request;
import br.ufsm.csi.app.service.RequestService;

@RestController
@RequestMapping("request")
public class RequestController {
  @Autowired
  private RequestService requestService;

  @GetMapping
  public List<Request> listRequests() {
    return requestService.listRequests();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Request> getRequest(@PathVariable Long id) {
    return requestService.getRequest(id);
  }

  @PostMapping("/new")
  @Transactional
  public ResponseEntity<?> newRequest(@RequestBody RequestForm values, UriComponentsBuilder uBuilder) {
    return requestService.newRequest(values, uBuilder);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<String> changeStatus(@PathVariable Long id, @RequestBody StatusForm status) {
    return requestService.changeStatus(id, status);
  }
}

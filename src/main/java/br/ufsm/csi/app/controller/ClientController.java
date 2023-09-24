package br.ufsm.csi.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.model.Client;
import br.ufsm.csi.app.service.ClientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/clients")
public class ClientController {
  @Autowired
  private ClientService clientService;

  @GetMapping("/{id}")
  public ResponseEntity<Client> client(@PathVariable Long id) {
    return clientService.getClient(id);
  }

  @GetMapping()
  public ResponseEntity<?> getClients() {
    return clientService.getClients();
  }

  @PostMapping("/new")
  public ResponseEntity<?> newClient(@RequestBody Client values,
      UriComponentsBuilder uriComponentsBuilder) {
    return clientService.newClient(values, uriComponentsBuilder);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody Client values) {
    return clientService.updateClient(id, values);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> desactiveClient(@PathVariable Long id) {
    return clientService.desactiveClient(id);
  }

}

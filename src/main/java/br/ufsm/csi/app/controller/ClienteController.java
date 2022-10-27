package br.ufsm.csi.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import javax.transaction.Transactional;

import br.ufsm.csi.app.model.Client;
import br.ufsm.csi.app.repository.ClientRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/clients")
public class ClienteController {

  @Autowired
  private ClientRepository clientRepository;

  @GetMapping("/{id}")
  public ResponseEntity<Client> client(@PathVariable Long id) {
    Client client = clientRepository.getReferenceById(id);

    return ResponseEntity.ok(client);
  }

  @PostMapping("/new")
  @Transactional
  public ResponseEntity<Client> newClient(@RequestBody Client values,
      UriComponentsBuilder uriComponentsBuilder) {
    Client client = new Client(values.getname());

    clientRepository.save(client);
    URI uri = uriComponentsBuilder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();

    return ResponseEntity.created(uri).body(client);
  }

}

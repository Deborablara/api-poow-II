package br.ufsm.csi.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import br.ufsm.csi.app.model.Client;
import br.ufsm.csi.app.repository.ClientRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/clients")
public class ClientController {

  @Autowired
  private ClientRepository clientRepository;

  @GetMapping("/{id}")
  public ResponseEntity<Client> client(@PathVariable Long id) {
    Client client = clientRepository.getReferenceById(id);

    return ResponseEntity.ok(client);
  }

  @GetMapping()
  public List<Client> getClients() {
    List<Client> clients = clientRepository.findAll();

    return clients;
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

  @PutMapping("/update/{id}")
  @Transactional
  public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client values) {
    System.out.println(values.getname());
    Client client = clientRepository.getReferenceById(id);
    client.setname(values.getname());

    return ResponseEntity.ok(client);
  }

  @PutMapping("/{id}")
  @Transactional
  public int desactiveClient(@PathVariable Long id) {
    return clientRepository.desactiveClient(id);
  }

}

package br.ufsm.csi.app.service;

import br.ufsm.csi.app.utils.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import br.ufsm.csi.app.model.Client;
import br.ufsm.csi.app.repository.ClientRepository;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  public ResponseEntity<Client> getClient(Long id) {

    try {
      Client client = clientRepository.getReferenceById(id);
      return ResponseEntity.ok(client);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  public ResponseEntity<?> getClients() {
    try {
      List<Client> clients = clientRepository.findAll();
      return ResponseEntity.ok(clients);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }

  }

  @Transactional
  public ResponseEntity<?> newClient(Client values,
      UriComponentsBuilder uriComponentsBuilder) {

    try {
      Client client = new Client(values.getname());
      clientRepository.save(client);
      URI uri = uriComponentsBuilder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();

      return ResponseEntity.created(uri).body(client);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Falha ao criar novo cliente");
    }

  }

  @Transactional
  public ResponseEntity<?> updateClient(Long id, Client values) {
    try {
      Client client = clientRepository.getReferenceById(id);
      client.setname(values.getname());

      return ResponseEntity.ok(client);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Falha ao atualizar cliente");
    }

  }

  @Transactional
  public ResponseEntity<?> desactiveClient(Long id) {
    try {
      clientRepository.desactiveClient(id);
      return ResponseEntity.ok().body(new DefaultResponse("Cliente desativado com sucesso", 200));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Falha ao desativar este cliente");
    }

  }
}

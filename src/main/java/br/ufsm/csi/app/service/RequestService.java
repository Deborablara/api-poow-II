package br.ufsm.csi.app.service;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.forms.RequestForm;
import br.ufsm.csi.app.forms.StatusForm;
import br.ufsm.csi.app.model.Request;
import br.ufsm.csi.app.model.Status;
import br.ufsm.csi.app.repository.ClientRepository;
import br.ufsm.csi.app.repository.ProductRepository;
import br.ufsm.csi.app.repository.RequestRepository;
import br.ufsm.csi.app.repository.VehicleRepository;

@Service
public class RequestService {
  @Autowired
  private RequestRepository requestRepository;
  @Autowired
  private VehicleRepository vehicleRepository;
  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private ProductRepository productRepository;

  public List<Request> listRequests() {
    List<Request> requests = requestRepository.findAll();
    return requests;
  }

  public ResponseEntity<Request> getRequest(Long id) {
    Request request = requestRepository.getReferenceById(id);
    return ResponseEntity.ok(request);
  }

  @Transactional
  public ResponseEntity<?> newRequest(RequestForm values, UriComponentsBuilder uBuilder) {
    try {
      Request request = values.newRequest(productRepository, vehicleRepository, clientRepository);
      requestRepository.save(request);

      URI uri = uBuilder.path("/request/{id}").buildAndExpand(request.getId()).toUri();

      return ResponseEntity.created(uri).body(request);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Falha ao criar nova solicitação");
    }
  }

  @Transactional
  public ResponseEntity<String> changeStatus(Long id, StatusForm status) {
    try {
      Status s = Status.valueOf(status.getStatus());
      int result = requestRepository.changeStatus(s, id);
      if (result > 0) {
        return ResponseEntity.ok("Status alterado com sucesso");
      } else {
        return ResponseEntity.internalServerError().body("Falha ao alterar o status");
      }
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Erro ao processar a solicitação");
    }
  }
}

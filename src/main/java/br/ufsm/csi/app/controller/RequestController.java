package br.ufsm.csi.app.controller;

import java.net.URI;
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
import br.ufsm.csi.app.model.Request;
import br.ufsm.csi.app.model.Status;
import br.ufsm.csi.app.repository.ClientRepository;
import br.ufsm.csi.app.repository.ProductRepository;
import br.ufsm.csi.app.repository.RequestRepository;
import br.ufsm.csi.app.repository.VehicleRepository;

@RestController
@RequestMapping("request")
public class RequestController {
  @Autowired
  private RequestRepository requestRepository;
  @Autowired
  private VehicleRepository vehicleRepository;
  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private ProductRepository productRepository;

  @GetMapping
  public List<Request> listRequests() {
    List<Request> requests = requestRepository.findAll();

    return requests;
  }

  @GetMapping("/{id}")
  public Request getRequest(@PathVariable Long id) {
    Request request = requestRepository.getReferenceById(id);
    return request;
  }

  @PostMapping("/new")
  @Transactional
  public ResponseEntity<?> newRequest(@RequestBody RequestForm values, UriComponentsBuilder uBuilder) {
    Request request = values.newRequest(productRepository, vehicleRepository, clientRepository);
    requestRepository.save(request);

    URI uri = uBuilder.path("/request/{id}").buildAndExpand(request.getId()).toUri();

    return ResponseEntity.created(uri).body(request);
  }

  // @PutMapping("/{id}")
  // @Transactional
  // public int changeStatus(@PathVariable Long id, @RequestBody Enum<Status>
  // status) {
  // return requestRepository.changeStatus(status, id);
  // }
}

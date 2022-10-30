package br.ufsm.csi.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.app.model.Request;
import br.ufsm.csi.app.repository.RequestRepository;

@RestController
@RequestMapping("request")
public class RequestController {
  @Autowired
  private RequestRepository requestRepository;

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
}

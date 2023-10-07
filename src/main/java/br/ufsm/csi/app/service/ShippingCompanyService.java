package br.ufsm.csi.app.service;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.model.ShippingCompany;
import br.ufsm.csi.app.repository.ShippingCompanyRepository;

@Service
public class ShippingCompanyService {

  @Autowired
  private ShippingCompanyRepository shippingCompanyRepository;

  public List<ShippingCompany> getShippingCompanies() {
    return shippingCompanyRepository.findAll();
  }

  public ResponseEntity<ShippingCompany> getShippingCompany(Long id) {
    ShippingCompany shippingCompany = shippingCompanyRepository.getReferenceById(id);
    return ResponseEntity.ok(shippingCompany);
  }

  @Transactional
  public int desactiveShippingCompany(Long id) {
    return shippingCompanyRepository.desactiveShippingCompany(id);
  }

  @Transactional
  public ResponseEntity<?> newShippingCompany(ShippingCompany values, UriComponentsBuilder uBuilder) {
    ShippingCompany shippingCompany = new ShippingCompany(values.getName(), values.getActive());
    shippingCompanyRepository.save(shippingCompany);
    URI uri = uBuilder.path("/shipping-company/{id}").buildAndExpand(shippingCompany.getId()).toUri();
    return ResponseEntity.created(uri).body(shippingCompany);
  }

  @Transactional
  public ResponseEntity<ShippingCompany> updateShippingCompany(Long id, ShippingCompany values) {
    ShippingCompany shippingCompany = shippingCompanyRepository.getReferenceById(id);
    shippingCompany.setName(values.getName());
    shippingCompany.setActive(values.getActive());
    return ResponseEntity.ok(shippingCompany);
  }
}

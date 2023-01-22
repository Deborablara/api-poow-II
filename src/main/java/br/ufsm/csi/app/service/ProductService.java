package br.ufsm.csi.app.service;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.model.Product;
import br.ufsm.csi.app.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public ResponseEntity<?> productsList() {
    try {
      List<Product> products = productRepository.findAll();

      return ResponseEntity.ok().body(products);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  public ResponseEntity<?> getProduct(Long id) {
    try {
      Product product = productRepository.getReferenceById(id);
      return ResponseEntity.ok().body(product);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @Transactional
  public ResponseEntity<?> desactiveProduct(Long id) {

    try {
      productRepository.desactiveProduct(id);
      return ResponseEntity.ok().body("Produto desativado com sucesso");
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Falha ao desativar esse produto");
    }
  }

  public ResponseEntity<?> updateProduct(Long id, Product values) {
    try {
      Product product = productRepository.getReferenceById(id);
      product.setname(values.getname());
      product.setDescription(values.getDescription());
      product.setActive(values.getActive());

      return ResponseEntity.ok().body(product);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Falha ao atualizar este produto");
    }

  }

  @Transactional
  public ResponseEntity<?> newProduct(
      Product values,
      UriComponentsBuilder uBuilder) {

    try {
      Product product = new Product(values.getname(), values.getDescription(), values.getActive());

      productRepository.save(product);
      URI uri = uBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();

      return ResponseEntity.created(uri).body(product);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Falha ao criar este produto");
    }

  }

}

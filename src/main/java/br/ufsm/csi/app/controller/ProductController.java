package br.ufsm.csi.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.model.Product;
import br.ufsm.csi.app.repository.ProductRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @GetMapping()
  public List<Product> productList() {
    List<Product> products = productRepository.findAll();

    return products;
  }

  @GetMapping("/{id}")
  public Product product(@PathVariable Long id) {
    Product product = productRepository.getReferenceById(id);

    return product;
  }

  @DeleteMapping("{id}")
  @Transactional
  public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
    productRepository.deleteById(id);

    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<?> desactiveProdcut(@PathVariable Long id) {
    Product product = productRepository.getReferenceById(id);
    product.setActive(false);

    productRepository.save(product);

    return ResponseEntity.ok().build();
  }

  @PutMapping("/update/{id}")
  @Transactional
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product values) {
    Product product = productRepository.getReferenceById(id);
    product.setname(values.getname());
    product.setDescription(values.getDescription());
    product.setActive(values.getActive());

    return ResponseEntity.ok(product);
  }

  @PostMapping("/new")
  @Transactional
  public ResponseEntity<Product> newProduct(
      @RequestBody Product values,
      UriComponentsBuilder uBuilder) {
    Product product = new Product(values.getname(), values.getDescription(), values.getActive());

    productRepository.save(product);
    URI uri = uBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();

    return ResponseEntity.created(uri).body(product);
  }

}

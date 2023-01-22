package br.ufsm.csi.app.controller;

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
import br.ufsm.csi.app.service.ProductService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping()
  public ResponseEntity<?> productList() {
    return productService.productsList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> product(@PathVariable Long id) {
    return productService.getProduct(id);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
    return productService.desactiveProduct(id);
  }

  @PutMapping("/update/{id}")
  @Transactional
  public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product values) {
    return productService.updateProduct(id, values);
  }

  @PostMapping("/new")
  @Transactional
  public ResponseEntity<?> newProduct(
      @RequestBody Product values,
      UriComponentsBuilder uBuilder) {
    return productService.newProduct(values, uBuilder);
  }

}

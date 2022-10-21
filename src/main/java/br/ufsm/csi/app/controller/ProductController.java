package br.ufsm.csi.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.app.model.Product;
import br.ufsm.csi.app.repository.ProductRepository;

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

}

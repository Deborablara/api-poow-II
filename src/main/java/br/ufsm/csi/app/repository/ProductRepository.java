package br.ufsm.csi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Modifying
  @Query("UPDATE Product p SET p.active=false WHERE p.id in ?1")
  int desactiveProduct(Long id);
}

package br.ufsm.csi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

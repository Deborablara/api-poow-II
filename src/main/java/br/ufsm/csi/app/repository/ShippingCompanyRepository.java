package br.ufsm.csi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.app.model.ShippingCompany;

public interface ShippingCompanyRepository extends JpaRepository<ShippingCompany, Long>{

    @Modifying
    @Query("UPDATE ShippingCompany s SET s.active=false WHERE s.id in ?1")
    int  desactiveShippingCompany(Long id);

} 

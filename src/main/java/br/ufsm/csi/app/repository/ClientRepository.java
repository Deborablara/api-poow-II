package br.ufsm.csi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.app.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    
  @Modifying
  @Query("UPDATE Client c SET c.active=false WHERE c.id in ?1")
    int desactiveClient(Long id);

}

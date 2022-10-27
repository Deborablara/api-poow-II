package br.ufsm.csi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufsm.csi.app.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

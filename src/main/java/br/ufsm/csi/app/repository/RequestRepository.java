package br.ufsm.csi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufsm.csi.app.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

}

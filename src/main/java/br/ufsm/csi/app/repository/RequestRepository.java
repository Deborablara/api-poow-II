package br.ufsm.csi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.app.model.Request;
import br.ufsm.csi.app.model.Status;

public interface RequestRepository extends JpaRepository<Request, Long> {

  @Modifying
  @Query("UPDATE Request r SET r.status = ?1 WHERE r.id = ?2")
  int changeStatus(Enum<Status> status, Long id);

}

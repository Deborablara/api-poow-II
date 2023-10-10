package br.ufsm.csi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufsm.csi.app.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {

}

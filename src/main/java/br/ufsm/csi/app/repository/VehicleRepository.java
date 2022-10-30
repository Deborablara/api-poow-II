package br.ufsm.csi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.app.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

  @Modifying
  @Query("UPDATE Vehicle v SET v.active=false WHERE v.id in ?1")
  int desactiveVehicle(Long id);

}

package br.ufsm.csi.app.service;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.forms.VehicleForm;
import br.ufsm.csi.app.model.Vehicle;
import br.ufsm.csi.app.repository.ShippingCompanyRepository;
import br.ufsm.csi.app.repository.VehicleRepository;

@Service
public class VehicleService {

  @Autowired
  private VehicleRepository vehicleRepository;
  @Autowired
  private ShippingCompanyRepository shippingCompanyRepository;

  public ResponseEntity<Vehicle> getVehicle(Long id) {
    try {
      Vehicle vehicle = vehicleRepository.getReferenceById(id);
      return ResponseEntity.ok(vehicle);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  public ResponseEntity<List<Vehicle>> getVehicles() {
    try {
      List<Vehicle> vehicles = vehicleRepository.findAll();
      return ResponseEntity.ok(vehicles);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @Transactional
  public ResponseEntity<?> newVehicle(@RequestBody VehicleForm values, UriComponentsBuilder uBuilder) {
    try {
      Vehicle vehicle = values.newVehicle(shippingCompanyRepository);
      vehicleRepository.save(vehicle);
      URI uri = uBuilder.path("vehicle/{id}").buildAndExpand(vehicle.getId()).toUri();
      return ResponseEntity.created(uri).body(vehicle);

    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Falha ao criar novo veículo");
    }
  }

  @Transactional
  public ResponseEntity<?> desactiveVehicle(Long id) {
    try {
      vehicleRepository.desactiveVehicle(id);
      return ResponseEntity.ok().body("Veículo desativado com sucesso");
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Falha ao desativar este veículo");
    }

  }

}

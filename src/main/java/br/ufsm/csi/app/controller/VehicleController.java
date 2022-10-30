package br.ufsm.csi.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.forms.VehicleForm;
import br.ufsm.csi.app.model.Vehicle;
import br.ufsm.csi.app.repository.ShippingCompanyRepository;
import br.ufsm.csi.app.repository.VehicleRepository;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

  @Autowired
  private VehicleRepository vehicleRepository;
  @Autowired
  private ShippingCompanyRepository shippingCompanyRepository;

  @GetMapping()
  public List<Vehicle> listVehicles() {
    List<Vehicle> vehicles = vehicleRepository.findAll();
    return vehicles;
  }

  @GetMapping("/{id}")
  public Vehicle listVehicle(@PathVariable Long id) {
    Vehicle vehicle = vehicleRepository.getReferenceById(id);
    return vehicle;
  }

  @PutMapping("/{id}")
  @Transactional
  public int desactiveVehicle(@PathVariable Long id) {
    return vehicleRepository.desactiveVehicle(id);
  }

  @PostMapping("new")
  @Transactional
  public ResponseEntity<?> newVehicle(@RequestBody VehicleForm values, UriComponentsBuilder uBuilder) {

    Vehicle vehicle = values.convert(shippingCompanyRepository);
    vehicleRepository.save(vehicle);

    vehicleRepository.save(vehicle);
    URI uri = uBuilder.path("vehicle/{id}").buildAndExpand(vehicle.getId()).toUri();

    return ResponseEntity.created(uri).body(vehicle);
  }

  @PutMapping("/update/{id}")
  @Transactional
  public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody VehicleForm values) {
    Vehicle vehicle = values.update(shippingCompanyRepository, vehicleRepository, id);

    return ResponseEntity.ok(vehicle);
  }

}

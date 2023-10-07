package br.ufsm.csi.app.controller;

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
import br.ufsm.csi.app.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @GetMapping()
  public ResponseEntity<List<Vehicle>> listVehicles() {
    return vehicleService.getVehicles();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Vehicle> listVehicle(@PathVariable Long id) {
    return vehicleService.getVehicle(id);
  }

  @PutMapping("update/{id}")
  @Transactional
  public ResponseEntity<?> desactiveVehicle(@PathVariable Long id) {
    return vehicleService.desactiveVehicle(id);
  }

  @PostMapping("new")
  @Transactional
  public ResponseEntity<?> newVehicle(@RequestBody VehicleForm values, UriComponentsBuilder uBuilder) {
    return vehicleService.newVehicle(values, uBuilder);
  }

}

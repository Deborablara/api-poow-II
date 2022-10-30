package br.ufsm.csi.app.forms;

import br.ufsm.csi.app.model.ShippingCompany;
import br.ufsm.csi.app.model.Vehicle;
import br.ufsm.csi.app.repository.ShippingCompanyRepository;
import br.ufsm.csi.app.repository.VehicleRepository;

public class VehicleForm {
  private String plateNumber;
  private Long shippingCompanyId;

  public VehicleForm() {
  }

  public VehicleForm(String plateNumber, Long shippingCompanyId) {
    this.plateNumber = plateNumber;
    this.shippingCompanyId = shippingCompanyId;
  }

  public String getPlateNumber() {
    return plateNumber;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public Long getShippingCompanyId() {
    return shippingCompanyId;
  }

  public void setShippingCompanyId(Long shippingCompanyId) {
    this.shippingCompanyId = shippingCompanyId;
  }

  public Vehicle convert(ShippingCompanyRepository shippingCompanyRepository) {
    ShippingCompany company = shippingCompanyRepository.getReferenceById(this.shippingCompanyId);

    return new Vehicle(this.plateNumber, company);
  }

  public Vehicle update(
      ShippingCompanyRepository shippingCompanyRepository,
      VehicleRepository vehicleRepository,
      Long id) {

    Vehicle vehicle = vehicleRepository.getReferenceById(id);
    ShippingCompany company = shippingCompanyRepository.getReferenceById(this.shippingCompanyId);

    vehicle.setPlateNumber(this.getPlateNumber());
    vehicle.setShippingCompany(company);

    return vehicle;
  }

}

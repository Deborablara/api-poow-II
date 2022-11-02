package br.ufsm.csi.app.forms;

import br.ufsm.csi.app.model.Client;
import br.ufsm.csi.app.model.Product;
import br.ufsm.csi.app.model.Request;
import br.ufsm.csi.app.model.Vehicle;
import br.ufsm.csi.app.repository.ClientRepository;
import br.ufsm.csi.app.repository.ProductRepository;
import br.ufsm.csi.app.repository.VehicleRepository;

public class RequestForm {
  private Float amount;
  private Long client_id;
  private Long product_id;
  private Long vehicle_id;

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public Long getClient_id() {
    return client_id;
  }

  public void setClient_id(Long client_id) {
    this.client_id = client_id;
  }

  public Long getProduct_id() {
    return product_id;
  }

  public void setProduct_id(Long product_id) {
    this.product_id = product_id;
  }

  public Long getVehicle_id() {
    return vehicle_id;
  }

  public void setVehicle_id(Long vehicle_id) {
    this.vehicle_id = vehicle_id;
  }

  public Request newRequest(
      ProductRepository productRepository,
      VehicleRepository vehicleRepository,
      ClientRepository clientRepository) {
    Product product = productRepository.getReferenceById(this.product_id);
    Vehicle vehicle = vehicleRepository.getReferenceById(this.vehicle_id);
    Client client = clientRepository.getReferenceById(this.client_id);

    return new Request(client, vehicle, product, getAmount());
  }

}

package br.ufsm.csi.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String plateNumber;
  @ManyToOne
  private ShippingCompany shippingCompany;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPlateNumber() {
    return plateNumber;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public ShippingCompany getShippingCompany() {
    return shippingCompany;
  }

  public void setShippingCompany(ShippingCompany shippingCompany) {
    this.shippingCompany = shippingCompany;
  }

}

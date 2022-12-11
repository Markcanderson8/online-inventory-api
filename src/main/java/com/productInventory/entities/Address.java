package com.productInventory.entities;

import static com.productInventory.constants.StringConstants.REQUIRED_FIELD;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@

    Entity
@Table(name = "addresses")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "street" + REQUIRED_FIELD)
  @Column(name = "street")
  private String street;

  @NotBlank(message = "city" + REQUIRED_FIELD)
  @Column(name = "city")
  private String city;

  @NotBlank(message = "state" + REQUIRED_FIELD)
  @Column(length = 2, name = "state")
  private String state;

  @NotNull(message = "zipCode" + REQUIRED_FIELD)
  @Column(name = "zipCode")
  private Integer zipCode;

  public Address() {}

  public Address(Long id, String street, String city, String state, Integer zipCode) {
    this.id = id;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Integer getZipCode() {
    return zipCode;
  }

  public void setZipCode(Integer zipCode) {
    this.zipCode = zipCode;
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", zipCode=" + zipCode +
        '}';
  }

  @JsonIgnore
  public boolean isEmpty() {
    return Objects.isNull(id) &&
        Objects.isNull(street) &&
        Objects.isNull(city) &&
        Objects.isNull(state) &&
        Objects.isNull(zipCode);
  }
}

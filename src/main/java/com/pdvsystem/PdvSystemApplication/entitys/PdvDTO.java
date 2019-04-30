package com.pdvsystem.PdvSystemApplication.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PdvDTO {

  @Id
  private int id;

  private String tradingName;

  private String ownerName;

  private String document;

  @OneToOne(cascade = CascadeType.ALL)
  private CoverageArea coverageArea;

  @OneToOne(cascade = CascadeType.ALL)
  private Address address;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getTradingName() {
    return tradingName;
  }

  public void setTradingName(final String tradingName) {
    this.tradingName = tradingName;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(final String ownerName) {
    this.ownerName = ownerName;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(final String document) {
    this.document = document;
  }

  public CoverageArea getCoverageArea() {
    return coverageArea;
  }

  public void setCoverageArea(final CoverageArea coverageArea) {
    this.coverageArea = coverageArea;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(final Address address) {
    this.address = address;
  }
}

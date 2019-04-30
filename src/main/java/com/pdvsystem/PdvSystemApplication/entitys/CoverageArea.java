package com.pdvsystem.PdvSystemApplication.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CoverageArea {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String type;

  @Column(length = 99999)
  private double[][][][] coordinates;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public double[][][][] getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(final double[][][][] coordinates) {
    this.coordinates = coordinates;
  }
}

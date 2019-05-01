package com.pdvsystem.PdvSystemApplication.entitys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class PdvDTO {

    @Id
    @Column(nullable = false)
    @JsonProperty(required = true)
    private int id;

    @Column(nullable = false)
    @JsonProperty(required = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String tradingName;

    @Column(nullable = false)
    @JsonProperty(required = true)
    private String ownerName;

    @Column(unique = true, nullable = false)
    @JsonProperty(required = true)
    private String document;

    @JsonProperty(required = true)
    @OneToOne(cascade = CascadeType.ALL)
    private CoverageArea coverageArea;

    @JsonProperty(required = true)
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

    @Override
    public String toString() {
        return "PdvDTO{" +
                "id=" + id +
                ", tradingName='" + tradingName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", document='" + document + '\'' +
                ", coverageArea=" + coverageArea +
                ", address=" + address +
                '}';
    }
}

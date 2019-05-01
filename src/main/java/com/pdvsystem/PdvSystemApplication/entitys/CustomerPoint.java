package com.pdvsystem.PdvSystemApplication.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerPoint {


    @JsonProperty(required = true)
    private double lat;

    @JsonProperty(required = true)
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}

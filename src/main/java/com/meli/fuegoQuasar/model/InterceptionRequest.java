package com.meli.fuegoQuasar.model;


import java.util.List;

public class InterceptionRequest {

    private List<SatelliteReport> satelliteList;

    public List<SatelliteReport> getSatelliteList() {
        return satelliteList;
    }

    public void setSatelliteList(List<SatelliteReport> satelliteList) {
        this.satelliteList = satelliteList;
    }
}

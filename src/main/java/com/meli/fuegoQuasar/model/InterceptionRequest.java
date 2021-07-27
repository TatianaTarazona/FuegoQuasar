package com.meli.fuegoQuasar.model;


import java.util.List;

/**
 * Request del reporte de cada satélite, contiene una lista de los
 * satélites y los datos interceptados.
 */
public class InterceptionRequest {

    private List<SatelliteReport> satelliteList;

    public List<SatelliteReport> getSatelliteList() {
        return satelliteList;
    }

    public void setSatelliteList(List<SatelliteReport> satelliteList) {
        this.satelliteList = satelliteList;
    }
}

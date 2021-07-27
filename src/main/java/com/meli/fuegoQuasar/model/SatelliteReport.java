package com.meli.fuegoQuasar.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Reporte de cada satélite
 * name: nombre del satélite
 * distance: distancia del satelite al emisor
 * message: mensaje interceptado por el satélite
 */
public class SatelliteReport {

    @ApiModelProperty(position = 0)
    private String name;
    @ApiModelProperty(position = 1)
    private float distance;
    @ApiModelProperty(position = 2)
    private String[] message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

    public SatelliteReport() {
    }

    public SatelliteReport(String name, float distance) {
        this.name = name;
        this.distance = distance;
    }

    public SatelliteReport(String name, float distance , String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }
}

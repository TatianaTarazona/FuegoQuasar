package com.meli.fuegoQuasar.model;

import java.util.List;


public class SatelliteReport {

    private String name;
    private float distance;
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

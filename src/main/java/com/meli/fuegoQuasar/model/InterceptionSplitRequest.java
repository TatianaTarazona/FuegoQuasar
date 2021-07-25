package com.meli.fuegoQuasar.model;

public class InterceptionSplitRequest {

    float distance;
    private String[] message;


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

    public InterceptionSplitRequest() {
        this.message = null;
    }
}

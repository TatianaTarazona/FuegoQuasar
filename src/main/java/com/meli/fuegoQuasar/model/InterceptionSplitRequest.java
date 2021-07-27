package com.meli.fuegoQuasar.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Request del reporte de los datos interceptados de cada satélite
 */
public class InterceptionSplitRequest {

    @ApiModelProperty(position = 0)
    float distance;
    @ApiModelProperty(position = 1)
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

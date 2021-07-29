package com.meli.fuegoQuasar.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Response que contiene los datos del emisor
 * position: Posición de la nave (fuente, emisor)
 * message: mensaje de auxilio interceptado
 */
public class InterceptionResponse {

    @ApiModelProperty(position = 0)
    private Position position;
    @ApiModelProperty(position = 1)
    private String message;

    public InterceptionResponse() {
    }

    public InterceptionResponse(Position position, String message) {
        this.setPosition(position);
        this.message = message;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

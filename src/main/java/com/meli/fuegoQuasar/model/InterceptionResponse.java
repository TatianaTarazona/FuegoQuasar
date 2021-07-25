package com.meli.fuegoQuasar.model;

public class InterceptionResponse {

    private Position position;
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

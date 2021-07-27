package com.meli.fuegoQuasar.model;

/**
 * Coordenadas de los elementos en la galaxia
 */
public class Position {

    private double x;
    private double y;


    public Position() {

    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(double[] points) {
        this.x = points[0];
        this.y = points[1];
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


}

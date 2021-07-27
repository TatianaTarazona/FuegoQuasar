package com.meli.fuegoQuasar.constant;

/**
 * Posición de los satélites actualmente en servicio
 */
public enum SatellitesPosition {

    KENOBI(-500,-200),
    SKYWALKER(100,-100),
    SATO(500,100);
    private final float x;
    private final float y;

    SatellitesPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

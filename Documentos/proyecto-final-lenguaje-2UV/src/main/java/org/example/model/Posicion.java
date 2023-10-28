package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Posicion {
    private double latitud;
    private double longitud;


    public Posicion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "latidud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}

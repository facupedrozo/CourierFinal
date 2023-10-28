package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Seguimiento {
    private LocalDate fechaYHora;
    private double latitud;
    private double longitud;
    private Pedido pedido;
    private Sector sector;
    private String numeroDeRastreo;

    public Seguimiento(LocalDate fechaYHora, double latitud, double longitud, Pedido pedido, Sector sector) {
        this.fechaYHora = fechaYHora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.pedido = pedido;
        this.sector = sector;
    }
    public Seguimiento(LocalDate fechaYHora, double latitud, double longitud) {
        this.fechaYHora = fechaYHora;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Seguimiento(Pedido pedido) {
        this.pedido = pedido;
    }
    public String generarNumeroRastreo() {
        // genera un identificador unical
        return UUID.randomUUID().toString();
    }


}

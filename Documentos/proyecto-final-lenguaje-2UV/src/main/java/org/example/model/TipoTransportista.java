package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoTransportista {

    private String descripcion;

    public TipoTransportista(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoTransportista() {
    }
}

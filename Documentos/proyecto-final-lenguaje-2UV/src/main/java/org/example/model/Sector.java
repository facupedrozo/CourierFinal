package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Sector {

    private String codigo;
    private String descripcion;
    private Estado estado;
    public enum Estado{
        HABILITADO,
        DESHABILITADO
    }


    public Sector(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = Estado.HABILITADO;

    }

    public Sector() {
    }

    @Override
    public String toString() {
        return "Sector{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

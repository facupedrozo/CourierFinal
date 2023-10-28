package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoriaProducto {
    private String nombre;

    public CategoriaProducto(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaProducto() {
    }

    @Override
    public String toString() {
        return "CategoriaProducto{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}

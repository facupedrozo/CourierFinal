package org.example.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Proveedor {
    private String cuit;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private Estado estado;


    public enum Estado{
        Habilitado,
        Invalido
    }

    public Proveedor(String cuit, String nombre, String direccion, String telefono, String email) {
        this.cuit = cuit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.estado = Estado.Habilitado;
    }

    @Override
    public String toString() {
        return "cuit: " + cuit + '\'' +
                ", nombre: " + nombre + '\'' +
                ", direccion: " + direccion + '\'' +
                ", telefono: " + telefono + '\'' +
                ", email: " + email + '\'' +
                ", estado: " + estado;
    }
}

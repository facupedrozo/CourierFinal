package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class Deposito {
    private String codigo;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String continente;
    private Posicion posicion;
    private Estado estado;

    private List<Sector> sectores;
    public enum Estado{
        HABILITADO,
        DESHABILITADO
    }

    public Deposito(String codigo, String nombre, String direccion, String telefono, String email, String continente,Posicion posicion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.continente = continente;
        this.sectores = new ArrayList<>();
        this.posicion=posicion;
        this.estado = Estado.HABILITADO;
    }

    public Deposito() {
        this.sectores = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Deposito{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", continente='" + continente + '\'' +
                '}';
    }
}

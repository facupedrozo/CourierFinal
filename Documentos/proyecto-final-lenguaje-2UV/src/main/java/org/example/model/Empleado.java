package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Empleado {
    private String CUIT;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private Estado estado;
    private Deposito deposito;
    public enum Estado{
        DESHABILITADO,
        HABILITADO

    }

    public Empleado(String CUIT, String nombre, String apellido, String direccion, String telefono, Deposito deposito) {
        this.CUIT = CUIT;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        estado = Estado.HABILITADO;
        this.deposito = deposito;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "CUIT='" + CUIT + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estado=" + estado +
                ", deposito=" + deposito +
                '}';
    }
}

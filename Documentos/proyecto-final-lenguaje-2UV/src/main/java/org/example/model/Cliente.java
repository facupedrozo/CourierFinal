package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Cliente {
    private String cuit;
    private String nombre;
    private String apellido;
    private List<Pedido> pedidos;
    private Estado estado;
    private String direccion;
    private String telefono;

    public enum Estado{
        HABILITADO,
        DESHABILITADO
    }


    public Cliente() {
        this.pedidos = new ArrayList<>();
    }

    public Cliente(String cuit, String nombre, String apellido, String direccion, String telefono) {
        this.cuit = cuit;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pedidos = new ArrayList<>();
        this.estado = Estado.HABILITADO;
        this.direccion = direccion;
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return "Cliente " +
                "cuit='" + cuit + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", estado=" + estado +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\''
                ;
    }
}

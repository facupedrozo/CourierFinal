package org.example.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Transportista {
    private String cuit;
    private String nombre;
    private String telefono;
    private String email;
    private Pedido pedido;
    private TipoTransportista tipo;
    private Estado estado;
    public enum Estado{
        HABILITADO,
        DESHABILITADO
    }
    public Transportista() {
    }

    public Transportista(String cuit, String nombre, String telefono, String email, TipoTransportista tipo) {
        this.cuit = cuit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
        this.estado = Estado.HABILITADO;
    }

    @Override
    public String toString() {
        return "Transportista{" +
                "cuit='" + cuit + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + getTipo().getDescripcion() + '\'' +

                '}';
    }
}

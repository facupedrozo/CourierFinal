package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {
    private String codigo;
    private String nombre;
    private double ancho;
    private double altura;
    private double largo;
    private double peso;
    private CategoriaProducto categoria;
    private boolean habilitado;
    private Proveedor proveedor;
    private Estado estado;
    public enum Estado{
        HABILITADO,
        DESHABILITADO
    }


    public Producto(String codigo, String nombre, double ancho, double altura, double largo, double peso, CategoriaProducto categoria, Proveedor proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ancho = ancho;
        this.altura = altura;
        this.largo = largo;
        this.peso = peso;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.habilitado = isHabilitado();
        this.estado = Estado.HABILITADO;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Producto() {
    }


    @Override
    public String toString() {
        return "Producto "+
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ancho=" + ancho +
                ", altura=" + altura +
                ", largo=" + largo +
                ", peso=" + peso +
                ", categoria=" + categoria +
                ", proveedor=" + proveedor +
                '}';
    }

}

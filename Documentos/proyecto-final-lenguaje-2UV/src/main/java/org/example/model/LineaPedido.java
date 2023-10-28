package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter

public class LineaPedido {
    private Producto producto;
    private int cantidad;
    private static String ultimoId;
    private String codigo;
    private double calificacion;
    private int calificacionProveedor;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.codigo = generarId();
    }
    private static String generarId() {
        // Aquí utilizamos la clase Random para generar un número aleatorio.
        // También se podría implementar una lógica más robusta para asegurar la unicidad.
        Random random = new Random();
        int nuevoIdInt = random.nextInt(10000); // Puedes ajustar el rango según tus necesidades.
        String nuevoId = String.valueOf(nuevoIdInt);

        // Si por algún motivo el ID generado ya existe, genera uno nuevo hasta que sea único.
        while (nuevoId.equals(ultimoId)) {
            nuevoIdInt = random.nextInt(10000);
            nuevoId = String.valueOf(nuevoIdInt);
        }

        // Actualiza el último ID generado para el siguiente producto.
        ultimoId = nuevoId;
        return nuevoId;
    }
    @Override
    public String toString() {
        return "LineaPedido{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }
}
